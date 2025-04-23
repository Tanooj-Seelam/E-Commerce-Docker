package com.example.ecommerce.repository;

import com.example.ecommerce.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    @Query("SELECT p FROM Product p WHERE"+
            "LOWER(p.name) LIKE LOWER(CONCAT('%', :keyword, '%')) OR"+
            "LOWER(p.description) LIKE LOWER(CONCAT('%', :keyword, '%')) OR"+
            "LOWER(p.category) LIKE LOWER(CONCAT('%', :Keyword, '%'))")
    List<Product> searchProducts(String keyword);
}
