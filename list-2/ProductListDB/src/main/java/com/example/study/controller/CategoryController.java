package com.example.study.controller;

import com.example.study.entity.Category;
import com.example.study.entity.Product;
import com.example.study.service.ProductListDBService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/category")
public class CategoryController {
    private final ProductListDBService productService;

    public CategoryController(ProductListDBService productService) {
        this.productService = productService;
    }


    @GetMapping({"/index","","/"})
    public String home(Model model) {
        List<Category> categoryList = productService.getCategoryRepository().findAll();
        model.addAttribute("categoryList", categoryList);
        return "category/index";
    }


    @GetMapping("/add")
    public String add(Model model) {
        model.addAttribute("category", new Category() );
        return "category/add";
    }

    @PostMapping("/add")
    public String add(@ModelAttribute Category category) {
        productService.getCategoryRepository().save(category);
        return "redirect:/category/";
    }

    @GetMapping("/{catId}/edit")
    public String edit(@PathVariable Long catId, Model model) {
        model.addAttribute("category", productService.getCategoryRepository().findById(catId));
        return "category/edit";
    }

    @PostMapping("/edit")
    public String edit(@ModelAttribute Category category) {
        Optional<Category> toEdit = productService.getCategoryRepository().findById(category.getCategoryId());
        if (toEdit.isPresent()) {
            Category toEditCategory = toEdit.get();
            toEditCategory.setCategoryCode(category.getCategoryCode());
            toEditCategory.setCategoryName(category.getCategoryName());
            productService.getCategoryRepository().save(toEditCategory);
        }
        return "redirect:/category/";
    }

    @PostMapping("/remove")
    public String remove(@RequestParam Long catId) {
        Optional<Category> toRemove = productService.getCategoryRepository().findById(catId);
        if (toRemove.isPresent()) {
            Category toRemoveCategory = toRemove.get();
            List<Product> toRemoveProducts = productService.getProductRepository().findByProductCategory(toRemoveCategory);
            for (Product p: toRemoveProducts) {
                productService.getProductRepository().delete(p);
            }
            productService.getCategoryRepository().deleteById(catId);
        }
        return "redirect:/category/";
    }
}
