package com.jonathan.service.item.service.impl;

import com.jonathan.service.item.client.ProductClientRest;
import com.jonathan.service.item.domain.Item;
import com.jonathan.service.item.domain.Product;
import com.jonathan.service.item.service.ItemService;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
@Primary // para indicar que implemente este servicio. Tambien se puede usar qualifier
public class ItemServiceFeign implements ItemService {

    private ProductClientRest clientFeign;

    @Override
    public List<Item> findAll() {
        return clientFeign.getAllProducts().stream().map(p -> new Item(p, 1)).collect(Collectors.toList());
    }

    @Override
    public Item findById(Long id, Integer amount) {
        return new Item(clientFeign.getProductById(id), 1);
    }

    @Override
    public Product save(Product product) {
        return this.clientFeign.saveProduct(product);
    }

    @Override
    public Product update(Product product, Long id) {

        return this.clientFeign.updateProduct(product, id);

    }

    @Override
    public void delete(Long id) {
        clientFeign.delete(id);
    }
}
