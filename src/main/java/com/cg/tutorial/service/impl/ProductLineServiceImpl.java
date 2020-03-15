package com.cg.tutorial.service.impl;

import com.cg.tutorial.model.ProductLine;
import com.cg.tutorial.repository.ProductLineRepository;
import com.cg.tutorial.service.IService;
import com.cg.tutorial.service.ProductLineService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Iterator;
import java.util.List;

public class ProductLineServiceImpl implements ProductLineService {
    @Autowired
    private ProductLineRepository productLineRepository;

    @Override
    public Iterable<ProductLine> findAll() {
        return  productLineRepository.findAll();
    }

    @Override
    public void save(ProductLine object) {
        productLineRepository.save(object);
    }

    @Override
    public ProductLine findById(long id) {
        return productLineRepository.findById(id).get();
    }



    @Override
    public void remove(long id) {
        productLineRepository.deleteById(id);
    }
}
