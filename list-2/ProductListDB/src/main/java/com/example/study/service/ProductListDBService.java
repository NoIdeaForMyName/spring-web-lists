package com.example.study.service;


//import com.example.study.optionModel.IdStringModel;
import com.example.study.entity.Category;
import com.example.study.entity.Product;
import com.example.study.repository.ProductRepository;
import com.example.study.repository.CategoryRepository;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Getter
public class ProductListDBService {
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
