package com.cg.tutorial.repository.rest;

import com.cg.tutorial.model.ProductLine;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RestProductLineRepository extends JpaRepository<ProductLine,Long> {
}
