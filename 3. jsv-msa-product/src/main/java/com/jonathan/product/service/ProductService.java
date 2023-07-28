package com.jonathan.product.service;

import com.jonathan.product.domain.Product;

import java.util.List;

public interface ProductService {

    Product saveProduct(Product product);
    List<Product> findAll();
    Product findById(Long id);

}
