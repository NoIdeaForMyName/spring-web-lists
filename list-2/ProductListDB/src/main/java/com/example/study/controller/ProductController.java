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
        product.setProductId(0); // protection against explicit ID identification
        if (product.getProductCategory() != null) {
            productService.getProductRepository().save(product);
        }
        return "redirect:/product/";
    }

    @GetMapping("/{productId}/edit")
    public String edit(@PathVariable Long productId, Model model) {
        model.addAttribute("product", productService.getProductRepository().findById(productId));
        model.addAttribute("categoryList", productService.getCategoryRepository().findAll());
        return "product/edit";
    }

    @PostMapping("/edit")
    public String edit(@ModelAttribute("product") Product product) {
        Optional<Product> toEdit = productService.getProductRepository().findById(product.getProductId());
        if (toEdit.isPresent()) {
            Product existingProduct = toEdit.get();
            existingProduct.setProductName(product.getProductName());
            existingProduct.setProductPrice(product.getProductPrice());
            existingProduct.setProductCategory(product.getProductCategory());
            productService.getProductRepository().save(existingProduct);
        }
        return "redirect:/product/";
    }

    @PostMapping("/remove")
    public String remove(@RequestParam Long productId) {
        Optional<Product> toRemove = productService.getProductRepository().findById(productId);
        if(toRemove.isPresent()){
            productService.getProductRepository().deleteById(productId);
        }
        return "redirect:/product/";
    }
}
