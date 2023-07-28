package com.jonathan.service.item.service;

import com.jonathan.service.item.domain.Item;

import java.util.List;

public interface ItemService {

    List<Item> findAll();
    Item findById(Long id, Integer amount);

}
