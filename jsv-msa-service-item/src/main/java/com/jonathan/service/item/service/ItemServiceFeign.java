package com.jonathan.service.item.service;

import com.jonathan.service.item.client.ProductClientRest;
import com.jonathan.service.item.domain.Item;
import com.jonathan.service.item.domain.Product;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service("serviceFeign")
@Primary // para indicar que implemente este servicio. Tambien se puede usar qualifier
public class ItemServiceFeign implements ItemService {

    private ProductClientRest clientRest;


    @Override
    public List<Item> findAll() {
        return clientRest.getAllProducts().stream().map(p -> new Item(p, 1)).collect(Collectors.toList());
    }

    @Override
    public Item findById(Long id, Integer amount) {
        return new Item(clientRest.getProductById(id), 1);
    }
}
