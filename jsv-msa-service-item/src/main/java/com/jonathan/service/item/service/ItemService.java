package com.jonathan.service.item.service;

import com.jonathan.service.item.domain.Item;
import com.jonathan.service.item.domain.Product;

import java.util.List;

public interface ItemService {

    List<Item> findAll();
    Item findById(Long id, Integer amount);

    Product save(Product product);

    Product update(Product product, Long id);

    void delete(Long id);

}
