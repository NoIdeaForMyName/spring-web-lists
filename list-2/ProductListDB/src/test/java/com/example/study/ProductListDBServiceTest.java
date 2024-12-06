package com.example.study;

import com.example.study.entity.Category;
import com.example.study.service.ProductListDBService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ProductListDBServiceTest {
    @Autowired
    private ProductListDBService productListDBService;

//    @Test
//    public void assignTeacherToCourseTest(){
//        Category category = Category.builder()
//                .firstName("Tim")
//                .lastName("Bim")
//                .faculty("Physics")
//                .build();
//        Course course= Course.builder()
//                .courseName("CourseTest")
//                .category(category)
//                .build();
//        productListDBService.getCategoryRepository().save(category);
//        productListDBService.getCourseRepository().save(course);
//    }
}
