package com.example.study.repository;

import com.example.study.entity.Category;
import com.example.study.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product,Long> {
    List<Product> findByProductCategory(Category category);
}
