package com.example.productlist.service;


//import com.example.study.optionModel.IdStringModel;

import com.example.productlist.entity.Category;
import com.example.productlist.entity.Product;
import com.example.productlist.repository.CategoryRepository;
import com.example.productlist.repository.ProductRepository;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Getter
public class ProductListAppService {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private CategoryRepository categoryRepository;

    public boolean isProductEntityInsertable(Product product) {
        return !product.getProductName().isEmpty();
    }

    public boolean isCategoryEntityInsertable(Category category) {
        return !category.getCategoryCode().isEmpty() && !category.getCategoryName().isEmpty();
    }

//    public List<IdStringModel> getTeacherOptions(){
//        List<IdStringModel> result=new ArrayList<>();
//        result.add(new IdStringModel(0,"---"));
//        List<Category> categoryList = categoryRepository.findAll();
//        for (Category category : categoryList) {
//            result.add( new IdStringModel(category.getTeacherId(), category.getFirstName()+" "+ category.getLastName()));
//        }
//        return result;
//    }
}
