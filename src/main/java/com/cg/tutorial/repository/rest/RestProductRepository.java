package com.cg.tutorial.repository.rest;

import com.cg.tutorial.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface RestProductRepository extends JpaRepository<Product,Long> {

}
