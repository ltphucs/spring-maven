package com.cg.tutorial.service;

import com.cg.tutorial.model.Product;
import com.cg.tutorial.service.IService;
import org.springframework.data.domain.Page;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ProductService extends IService<Product> {
    Page<Product> findAll(Pageable pageable);
    Page<Product> findAllByProductNameContaining(String productName,Pageable pageable);
}
