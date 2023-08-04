package com.jonathan.product.controller;

import com.jonathan.product.domain.Product;
import com.jonathan.product.service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/product")
@AllArgsConstructor
public class ProductController {

    private Environment env;
    private ProductService productService;

    @GetMapping("")
    public ResponseEntity<List<Product>> getProduct(){
        List<Product> products = this.productService.findAll().stream().map(p -> {
            p.setPort(Integer.parseInt(env.getProperty("local.server.port")));
            return p;
        }).collect(Collectors.toList());
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable(name = "id") Long id){
        Product product = this.productService.findById(id);
        product.setPort(Integer.parseInt(env.getProperty("local.server.port")));
        return new ResponseEntity<>(product, HttpStatus.OK);
    }

}
