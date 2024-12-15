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
@RequestMapping("/user/product")
public class UserProductController {
    private final ProductListAppService productService;

    public UserProductController(ProductListAppService productService) {
        this.productService = productService;
    }


    @GetMapping({"/index","","/"})
    public String home(Model model) {
        List<Product> productList = productService.getProductRepository().findAll();
        model.addAttribute("productList", productList);
        return "user/product/index";
    }

    @GetMapping("/{productId}/details")
    String displayProductDetails(@PathVariable Long productId, Model model) {
        Optional<Product> productOptional = productService.getProductRepository().findById(productId);
        if (productOptional.isPresent()) {
            model.addAttribute("product", productOptional.get());
            return "user/product/details";
        }
        return "redirect:/user/product/";
    }

}
