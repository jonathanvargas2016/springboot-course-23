package com.jonathan.service.item.controller;

import com.jonathan.service.item.domain.Item;
import com.jonathan.service.item.domain.Product;
import com.jonathan.service.item.service.ItemService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/item")
public class ItemController {

    private ItemService itemService; //dos servicios implementan itemService. Spring no sabe cual inyectar.

    @GetMapping("")
    public ResponseEntity<List<Item>> getAll() {
        return new ResponseEntity<>(itemService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}/amount/{amount}")
    @Retry(name = "anotherMethod", fallbackMethod = "anotherMethod")
    public ResponseEntity<Item> detail(@PathVariable Long id, @PathVariable Integer amount) {
        return new ResponseEntity<>(itemService.findById(id, amount), HttpStatus.OK);
    }

    public ResponseEntity<Item> anotherMethod(@PathVariable Long id, @PathVariable Integer amount, Throwable throwable) {
        System.out.println("ENTRO METODO ALTERNATIVO ---------------------->");
        Item item = new Item();
        Product product = new Product();
        product.setId(id);
        product.setName("Camara Sony");
        product.setPrice(new BigDecimal(1500));
        item.setAmount(amount);
        item.setProduct(product);
        return new ResponseEntity<>(item, HttpStatus.OK);
    }


}
