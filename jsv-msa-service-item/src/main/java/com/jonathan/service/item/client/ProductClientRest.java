package com.jonathan.service.item.client;

import com.jonathan.service.item.domain.Product;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "services-products")
public interface ProductClientRest {

    @GetMapping("/product") //mismo endpoint del productController
    public List<Product> getAllProducts();

    @GetMapping("/product/{id}")
    public Product getProductById(@PathVariable(name = "id") Long id);


    @PostMapping("/product/create")
    public Product saveProduct(@RequestBody Product product);

    @PutMapping("/product/edit/{id}")
    public Product updateProduct(@RequestBody Product product, @PathVariable Long id);

    @DeleteMapping("/product/delete/{id}")
    public void delete(@PathVariable Long id);


}
