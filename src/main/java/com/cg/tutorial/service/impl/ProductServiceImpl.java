package com.cg.tutorial.service.impl;

import com.cg.tutorial.model.Product;
import com.cg.tutorial.service.IService;

import java.util.List;

public class ProductServiceImpl implements IService<Product> {
    @Override
    public Iterable<Product> findAll() {
        return null;
    }

    @Override
    public void save(Product object) {

    }

    @Override
    public Product findById(long id) {
        return null;
    }


    @Override
    public void remove(long id) {

    }
}
