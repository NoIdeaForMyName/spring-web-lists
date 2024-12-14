package com.example.productlist.repository;

import com.example.productlist.entity.Category;
import com.example.productlist.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product,Long> {
    List<Product> findByProductCategory(Category category);
}
