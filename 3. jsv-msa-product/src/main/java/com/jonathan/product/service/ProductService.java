package com.jonathan.product.service;

import com.jonathan.product.domain.Product;

import java.util.List;

public interface ProductService {

    List<Product> findAll();
    Product findById(Long id);

    Product saveProduct(Product product);

    void deleteById(Long id);

}
