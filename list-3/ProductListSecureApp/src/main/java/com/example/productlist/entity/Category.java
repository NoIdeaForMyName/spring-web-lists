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
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long categoryId;
    @Column(unique = true, length = 10)
    private String categoryCode;
    @Column(nullable = false, length = 30)
    private String categoryName;

//    @OneToMany(
//            mappedBy = "productCategory"
//    )
//    private List<Product> productsOfCategory;
}
