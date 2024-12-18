package com.example.productlist.controller;

import com.example.productlist.entity.Category;
import com.example.productlist.entity.Product;
import com.example.productlist.service.ProductListAppService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/adm/category")
public class AdminCategoryController {
    private final ProductListAppService productService;

    public AdminCategoryController(ProductListAppService productService) {
        this.productService = productService;
    }


    @GetMapping({"/index","","/"})
    public String home(Model model) {
        List<Category> categoryList = productService.getCategoryRepository().findAll();
        model.addAttribute("categoryList", categoryList);
        return "adm/category/index";
    }


    @GetMapping("/add")
    public String add(Model model) {
        model.addAttribute("category", new Category() );
        return "adm/category/add";
    }

    @PostMapping("/add")
    public String add(@ModelAttribute Category category) {
        if (productService.isCategoryEntityInsertable(category) && productService.getCategoryRepository().findByCategoryCode(category.getCategoryCode()).isEmpty()) {
            category.setCategoryId(0);
            productService.getCategoryRepository().save(category);
        }
        return "redirect:/adm/category/";
    }

    @GetMapping("/{catId}/edit")
    public String edit(@PathVariable Long catId, Model model) {
        model.addAttribute("category", productService.getCategoryRepository().findById(catId));
        return "adm/category/edit";
    }

    @PostMapping("/edit")
    public String edit(@ModelAttribute Category category) {
        Optional<Category> toEdit = productService.getCategoryRepository().findById(category.getCategoryId());
        Optional<Category> sameCodeCategory = productService.getCategoryRepository().findByCategoryCode(category.getCategoryCode());
        if (productService.isCategoryEntityInsertable(category) && toEdit.isPresent() && (sameCodeCategory.isEmpty() || sameCodeCategory.get().getCategoryCode().equals(toEdit.get().getCategoryCode()))) {
            Category toEditCategory = toEdit.get();
            toEditCategory.setCategoryCode(category.getCategoryCode());
            toEditCategory.setCategoryName(category.getCategoryName());
            productService.getCategoryRepository().save(toEditCategory);
        }
        return "redirect:/adm/category/";
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
        return "redirect:/adm/category/";
    }
}
