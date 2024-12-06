package com.example.study.entity;

import lombok.*;
import javax.persistence.*;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long categoryId;
    @Column(unique = true, length = 10)
    private String categoryCode;
    @Column(nullable = false, length = 30)
    private String categoryName;

//    @OneToMany(
//            mappedBy = "productCategory"
//    )
//    private List<Product> productsOfCategory;
}
