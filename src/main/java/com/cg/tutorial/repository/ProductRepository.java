package com.cg.tutorial.repository;

import com.cg.tutorial.model.Product;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

public interface ProductRepository extends PagingAndSortingRepository<Product,Long> {
    @Override
    @Modifying
    @Query("update Product p set p.deleted=1 where p.id=:id")
    void deleteById(@Param("id") Long id);
}
