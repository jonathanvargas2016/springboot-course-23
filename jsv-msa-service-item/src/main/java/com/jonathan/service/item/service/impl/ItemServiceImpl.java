package com.jonathan.service.item.service.impl;

import com.jonathan.service.item.domain.Item;
import com.jonathan.service.item.domain.Product;
import com.jonathan.service.item.service.ItemService;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Primary
public class ItemServiceImpl implements ItemService {

    private RestTemplate clientRestTemplate;

    @Override
    public List<Item> findAll() {
        List<Product> products = Arrays.asList(clientRestTemplate.getForObject("http://services-products", Product[].class));
        return products.stream().map(p -> new Item(p, 1)).collect(Collectors.toList());
    }

    @Override
    public Item findById(Long id, Integer amount) {

        Map<String, String> pathVariable = new HashMap<>();
        pathVariable.put("id", id.toString());

        Product product = clientRestTemplate.getForObject("http://services-products/{id}", Product.class, pathVariable);

        return new Item(product, amount);
    }
}
