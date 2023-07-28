package com.jonathan.product.service.impl;

import com.jonathan.product.domain.Product;
import com.jonathan.product.repository.ProductRepository;
import com.jonathan.product.service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ProductServiceImpl implements ProductService {

    private ProductRepository productRepository;

    @Override
    public Product saveProduct(Product product) {
        return null;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Product> findAll() {

        return  (List<Product>) this.productRepository.findAll();
    }

    @Override
    public Product findById(Long id) {
        return this.productRepository.findById(id).orElse(null);
    }
}
