package com.example.study.controller;

import com.example.study.entity.Category;
import com.example.study.entity.Product;
import com.example.study.service.ProductListDBService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/product")
public class ProductController {
    private final ProductListDBService productService;

    public ProductController(ProductListDBService productService) {
        this.productService = productService;
    }


    @GetMapping({"/index","","/"})
    public String home(Model model) {
        List<Product> productList = productService.getProductRepository().findAll();
        model.addAttribute("productList", productList);
        return "product/index";
    }


    @GetMapping("/add")
    public String add(Model model) {
        model.addAttribute("product", new Product() );
        List<Category> categoryList = productService.getCategoryRepository().findAll();
        model.addAttribute("categoryList", categoryList);
        return "product/add";
    }

    @PostMapping("/add")
    public String add(@ModelAttribute("product") Product product) {
        System.out.print("POST WYKONANY");
        System.out.println(product);
        productService.getProductRepository().save(product);
        return "redirect:/product/";
    }
}
