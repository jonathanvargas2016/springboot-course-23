package com.jonathan.product.controller;

import com.jonathan.product.domain.Product;
import com.jonathan.product.service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/product")
@AllArgsConstructor
public class ProductController {

    private Environment env;

    //@Value("${server.port}")
    //private Integer port;
    private ProductService productService;

    @GetMapping("")
    public ResponseEntity<List<Product>> getProduct(@RequestParam(name = "name", required = false) String name, @RequestHeader(name = "token-request", required = false) String token) {
        System.out.println("Name ---->" + name);
        System.out.println("Token ---->" + token);
        List<Product> products = this.productService.findAll().stream().map(p -> {
            p.setPort(Integer.parseInt(env.getProperty("local.server.port")));
            //p.setPort(port);
            return p;
        }).collect(Collectors.toList());
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable(name = "id") Long id) {

//        if (id.equals(2L)) {
//            throw new IllegalStateException("Producto no encontrado");
//        }
//
//        if (id.equals(3L)) {
//            try {
//                TimeUnit.SECONDS.sleep(5L);
//            } catch (InterruptedException e) {
//                throw new RuntimeException(e);
//            }
//        }

        Product product = this.productService.findById(id);
        product.setPort(Integer.parseInt(env.getProperty("local.server.port")));
        //product.setPort(port);
        return new ResponseEntity<>(product, HttpStatus.OK);
    }

    @PostMapping("create")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Product> saveProduct(@RequestBody Product product) {
        return new ResponseEntity<>(productService.saveProduct(product), HttpStatus.OK);
    }

    @PutMapping("edit/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Product> updateProduct(@RequestBody Product product, @PathVariable Long id) {
        Product productFind = productService.findById(id);
        productFind.setName(product.getName());
        productFind.setPrice(product.getPrice());

        return new ResponseEntity<>(productService.saveProduct(productFind), HttpStatus.OK);
    }

    @DeleteMapping("delete/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        productService.deleteById(id);
    }


}
