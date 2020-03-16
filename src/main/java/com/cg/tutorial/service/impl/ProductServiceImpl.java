package com.cg.tutorial.service.impl;

import com.cg.tutorial.model.Product;
import com.cg.tutorial.repository.ProductRepository;
import com.cg.tutorial.service.IService;
import com.cg.tutorial.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.List;

public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductRepository productRepository;
    @Override
    public Iterable<Product> findAll() {
        return productRepository.findAll();
    }

    @Override
    public void save(Product object) {
        productRepository.save(object);
    }

    @Override
    public Product findById(long id) {
        return productRepository.findById(id).get();
    }


    @Override
    public void remove(long id) {
        productRepository.deleteById(id);

    }

    @Override
    public Page<Product> findAll(Pageable pageable) {
        return productRepository.findAll(pageable);
    }

    @Override
    public Page<Product> findAllByProductNameContaining(String productName,Pageable pageable) {
        return productRepository.findAllByProductNameContaining(productName,pageable);
    }
}
