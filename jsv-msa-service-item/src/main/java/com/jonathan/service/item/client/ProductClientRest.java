package com.jonathan.service.item.client;


import com.jonathan.service.item.configuration.AppConfiguration;
import com.jonathan.service.item.domain.Product;
import org.springframework.cloud.loadbalancer.annotation.LoadBalancerClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "services-products")
public interface ProductClientRest {

    @GetMapping("/product") //mismo endpoint del productController
    public List<Product> getAllProducts();

    @GetMapping("/product/{id}")
    public Product getProductById(@PathVariable(name = "id") Long id);
}
