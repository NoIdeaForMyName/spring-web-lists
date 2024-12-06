package com.example.study.entity;

import lombok.*;
import javax.persistence.*;
import java.util.List;

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
