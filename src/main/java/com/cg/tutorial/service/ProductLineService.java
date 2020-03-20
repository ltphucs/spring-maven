package com.cg.tutorial.service;

import com.cg.tutorial.model.ProductLine;

import java.util.List;

public interface ProductLineService extends IService<ProductLine> {
    List<ProductLine> findAllRest();
}
