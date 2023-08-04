package com.jonathan.service.item.client;

import com.jonathan.service.item.configuration.LoadBalancerConfiguration;
import com.jonathan.service.item.domain.Product;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.loadbalancer.annotation.LoadBalancerClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(value = "services-products", url = "http://localhost:8080/product")
public interface ProductClientRest {

    @GetMapping("") //mismo endpoint del productController
    public List<Product> getAllProducts();

    @GetMapping("/{id}")
    public Product getProductById(@PathVariable(name = "id") Long id);
}
