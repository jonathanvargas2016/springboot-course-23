package com.jonathan.service.item.client;

import com.jonathan.service.item.domain.Product;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "services-products", url = "http://localhost:8080/product")
public interface ProductClientRest {

    @GetMapping("") //mismo endpoint del productController
    public List<Product> getAllProducts();

    @GetMapping("/{id}")
    public Product getProductById(@PathVariable(name = "id") Long id);
}
