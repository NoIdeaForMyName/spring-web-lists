package com.example.productlist;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

@Controller
public class ProductListController {

    private final ProductService productService;

    public ProductListController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/product/")
    String displayProducts(Locale locale, Model model) {
        Date date = new Date();
        DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG,
                DateFormat.LONG, locale);
        String serverTime = dateFormat.format(date);
        model.addAttribute("serverTime", serverTime);
        model.addAttribute("productList", productService );
        return "product/index";
    }

    @GetMapping("/product/add")
    String addProduct(Model model) {
        Product toAddProduct = new Product();
        model.addAttribute("product", toAddProduct);
        model.addAttribute("usedIDs", productService.getUsedIDs());
        return "product/add";
    }

    @PostMapping("/product/add")
    String addProduct(@ModelAttribute("product") Product product, RedirectAttributes redirectAttributes) {
        boolean success = productService.add(product);

        if (!success) {
            redirectAttributes.addFlashAttribute("error", "Product could not be added. ID must be unique.");
            return "redirect:/product/add";
        }

        return "redirect:/product/";
    }


    @GetMapping("/product/{productId}/details")
    String displayProductDetails(@PathVariable Integer productId, Model model) {
        model.addAttribute("product", productService.getById(productId));
        return "product/details";
    }

    @GetMapping("/product/{productId}/edit")
    String editProduct(@PathVariable Integer productId, Model model) {
        model.addAttribute("product", productService.getById(productId));
        return "product/edit";
    }

    @PostMapping("/product/edit")
    String editProduct(@ModelAttribute("product") Product product) {
        productService.set(productService.getIndexById(product.getId()), product);
        return "redirect:/product/";
    }

    @PostMapping("/product/remove")
    //@GetMapping("/product/remove")
    String removeProduct(@ModelAttribute("product") Product product) {
        productService.remove(productService.getById(product.getId()));
        return "redirect:/product/";
    }

}
