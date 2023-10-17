package com.jonathan.service.item.service.impl;

import com.jonathan.service.item.client.ProductClientRest;
import com.jonathan.service.item.domain.Item;
import com.jonathan.service.item.domain.Product;
import com.jonathan.service.item.service.ItemService;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
//@Primary
public class ItemServiceImpl implements ItemService {

    private RestTemplate clientRestTemplate;

    @Override
    public List<Item> findAll() {
        List<Product> products = Arrays.asList(clientRestTemplate.getForObject("http://services-products/product/", Product[].class));
        return products.stream().map(p -> new Item(p, 1)).collect(Collectors.toList());
    }

    @Override
    public Item findById(Long id, Integer amount) {

        Map<String, String> pathVariable = new HashMap<>();
        pathVariable.put("id", id.toString());

        Product product = clientRestTemplate.getForObject("http://services-products/product/{id}", Product.class, pathVariable);

        return new Item(product, amount);
    }

    @Override
    public Product save(Product product) {
        HttpEntity<Product> body = new HttpEntity<Product>(product);
        ResponseEntity<Product> res = clientRestTemplate.exchange("http://services-products/product/create", HttpMethod.POST, body, Product.class);
        Product prodRes = res.getBody();
        return prodRes;
    }

    @Override
    public Product update(Product product, Long id) {

        Map<String, String> pathVariable = new HashMap<>();
        pathVariable.put("id", id.toString());

        HttpEntity<Product> body = new HttpEntity<Product>(product);
        ResponseEntity<Product> res = clientRestTemplate.exchange("http://services-products/product/edit/{id}",
                HttpMethod.PUT, body, Product.class, pathVariable);
        return res.getBody();
    }

    @Override
    public void delete(Long id) {
        Map<String, String> pathVariable = new HashMap<>();
        pathVariable.put("id", id.toString());

        clientRestTemplate.delete("http://services-products/product/delete/{id}", pathVariable);

    }
}
