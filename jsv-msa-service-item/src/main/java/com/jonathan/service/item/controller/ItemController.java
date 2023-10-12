package com.jonathan.service.item.controller;

import com.jonathan.service.item.domain.Item;
import com.jonathan.service.item.domain.Product;
import com.jonathan.service.item.service.ItemService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.timelimiter.annotation.TimeLimiter;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.circuitbreaker.CircuitBreakerFactory;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

@RestController
@AllArgsConstructor
@RequestMapping("/item")
@RefreshScope // permite refrescar los componentes,
public class ItemController {
    private CircuitBreakerFactory cbFactory;
    private Environment env;
    private static Logger log = LoggerFactory.getLogger(ItemController.class);


    private final Logger logger = LoggerFactory.getLogger(ItemController.class);

    private ItemService itemService; //dos servicios implementan itemService. Spring no sabe cual inyectar.

    @GetMapping("")
    public ResponseEntity<List<Item>> getAll() {

        return new ResponseEntity<>(itemService.findAll(), HttpStatus.OK);
    }

//    @GetMapping("/{id}/amount/{amount}")
//    @Retry(name = "anotherMethod", fallbackMethod = "anotherMethod")
//    public ResponseEntity<Item> detail(@PathVariable Long id, @PathVariable Integer amount) {
//        return new ResponseEntity<>(itemService.findById(id, amount), HttpStatus.OK);
//    }


//    public ResponseEntity<Item> anotherMethod(@PathVariable Long id, @PathVariable Integer amount, Throwable throwable) {
//        System.out.println("ENTRO METODO ALTERNATIVO ---------------------->");
//        Item item = new Item();
//        Product product = new Product();
//        product.setId(id);
//        product.setName("Camara Sony");
//        product.setPrice(new BigDecimal(1500));
//        item.setAmount(amount);
//        item.setProduct(product);
//
//        return new ResponseEntity<>(item, HttpStatus.OK)
//
//    }

//    @GetMapping("/{id}/amount/{amount}")
//    @Retry(name = "anotherMethod", fallbackMethod = "anotherMethod")
//    public CompletableFuture<ResponseEntity<Item>> detail(@PathVariable Long id, @PathVariable Integer amount) {
//
//        return CompletableFuture.supplyAsync(() -> {
//            try {
//                Thread.sleep(3000);
//            } catch (InterruptedException e) {
//                Thread.currentThread().interrupt();
//            }
//            return new ResponseEntity<>(itemService.findById(id, amount), HttpStatus.OK);
//
//        });
//
//    }

//    public CompletableFuture<ResponseEntity<Item>> anotherMethod(@PathVariable Long id, @PathVariable Integer amount, Throwable throwable) {
//        System.out.println("ENTRO METODO ALTERNATIVO ---------------------->");
//        Item item = new Item();
//        Product product = new Product();
//        product.setId(id);
//        product.setName("Camara Sony");
//        product.setPrice(new BigDecimal(1500));
//        item.setAmount(amount);
//        item.setProduct(product);
//
//        return CompletableFuture.completedFuture(
//                new ResponseEntity<>(item, HttpStatus.OK)
//        );
//    }


    @GetMapping("/{id}/amount/{amount}")
    public ResponseEntity<Item> detail(@PathVariable Long id, @PathVariable Integer amount) {
        return cbFactory.create("items").run(() -> new ResponseEntity<>(itemService.findById(id, amount), HttpStatus.OK), e -> anotherMethod(id, amount, e));
    }


    //anotacion
    @CircuitBreaker(name = "items", fallbackMethod = "anotherMethod")
    // usando esta anotacion solo se aplica con la config en los archivos .properties o yml
    @GetMapping("/watch/{id}/amount/{amount}")
    public ResponseEntity<Item> detail2(@PathVariable Long id, @PathVariable Integer amount) {
        return new ResponseEntity<>(itemService.findById(id, amount), HttpStatus.OK);

    }

    @CircuitBreaker(name = "items", fallbackMethod = "anotherMethod2")
    @TimeLimiter(name = "items")
    // llamada en futuro. No puede calcular cuanto se demora por eso es necesario envolver esta llamada dentro de una clase especial
    @GetMapping("/watch3/{id}/amount/{amount}")
    public CompletableFuture<ResponseEntity<Item>> detail3(@PathVariable Long id, @PathVariable Integer amount) {
        return CompletableFuture.supplyAsync(() -> new ResponseEntity<>(itemService.findById(id, amount), HttpStatus.OK));
    }


    public ResponseEntity<Item> anotherMethod(@PathVariable Long id, @PathVariable Integer amount, Throwable e) {
        logger.info(e.getMessage());
        Item item = new Item();
        Product product = new Product();
        product.setId(id);
        product.setName("Camara Sony");
        product.setPrice(new BigDecimal(1500));
        item.setAmount(amount);
        item.setProduct(product);

        return new ResponseEntity<>(item, HttpStatus.OK);

    }

    public CompletableFuture<ResponseEntity<Item>> anotherMethod2(@PathVariable Long id, @PathVariable Integer amount, Throwable e) {
        logger.info(e.getMessage());
        Item item = new Item();
        Product product = new Product();
        product.setId(id);
        product.setName("Camara Sony");
        product.setPrice(new BigDecimal(1500));
        item.setAmount(amount);
        item.setProduct(product);

        return CompletableFuture.supplyAsync(() -> new ResponseEntity<>(item, HttpStatus.OK));
    }

    @GetMapping("get-config")
    public ResponseEntity<?> getConfig(@Value("${server.port}") String port){
        //log.info(configurationProperties.getText());
        Map<String, String> json = new HashMap<>();
        //json.put("text", configurationProperties.getText());
        json.put("port", port);

        if(env.getActiveProfiles().length > 0 && env.getActiveProfiles()[0].equals("dev")){
            json.put("author", env.getProperty("configuration.author.name"));
            json.put("email", env.getProperty("configuration.author.email"));
        }


        return new ResponseEntity<Map<String, String>>(json, HttpStatus.OK);
    }


}
