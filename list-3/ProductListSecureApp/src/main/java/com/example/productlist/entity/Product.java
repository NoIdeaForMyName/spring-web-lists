package com.example.productlist.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long productId;
    @Column(nullable = false, length = 30)
    private String productName;
    @Column(nullable = false)
    private float productWeight;
    @Column(nullable = false)
    private float productPrice;

    @ManyToOne
    @JoinColumn(name="fk_product_categoryId", nullable = false)
    private Category productCategory;
}
