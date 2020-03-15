package com.cg.tutorial.repository;

import com.cg.tutorial.model.ProductLine;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;



public interface ProductLineRepository extends PagingAndSortingRepository<ProductLine, Long> {
    @Override
    @Modifying
    @Query("update ProductLine p set p.deleted=1 where p.id=:id")
    void deleteById(@Param("id") Long id);
}
