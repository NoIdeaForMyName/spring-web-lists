package com.example.productlist.controller;

import com.example.productlist.cart.Cart;
import com.example.productlist.cart.CartItem;
import com.example.productlist.cart.CartProduct;
import com.example.productlist.cart.CartService;
import com.example.productlist.entity.Category;
import com.example.productlist.entity.Product;
import com.example.productlist.service.ProductListAppService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/user/product")
public class UserProductController {
    private final ProductListAppService productService;
    private final CartService cartService;

    public UserProductController(ProductListAppService productService, CartService cartService) {
        this.productService = productService;
        this.cartService = cartService;
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


    @PostMapping("/add-to-cart")
    public String addToCart(@RequestParam Long productId, @RequestParam(defaultValue = "1") int quantity,
                            HttpServletRequest request, HttpServletResponse response) {
        Cart cart = cartService.getCartFromCookies(request);
        cart.addItem(productId, quantity);
        cartService.saveCartToCookies(response, cart);
        return "redirect:/user/product/index";
    }

    @GetMapping("/cart")
    public String viewCart(Model model, HttpServletRequest request) {
        Cart cart = cartService.getCartFromCookies(request);
        List<CartItem> cartItems = cart.getItems();
        List<CartProduct> cartProducts = cartItems.stream()
                .map(item -> new CartProduct(productService.getProductRepository().findById(item.getProductId()).orElse(null), item.getQuantity()))
                .filter(cartProduct -> cartProduct != null)
                .toList();


        model.addAttribute("cart", cart);
        model.addAttribute("cart_products", cartProducts);
        return "user/product/cart";
    }


    @PostMapping("/cart/remove")
    public String removeFromCart(@RequestParam Long productId, HttpServletRequest request, HttpServletResponse response) {
        Cart cart = cartService.getCartFromCookies(request);
        cart.removeItem(productId);
        cartService.saveCartToCookies(response, cart);
        return "redirect:/user/product/cart";
    }

    @PostMapping("/cart/clear")
    public String clearCart(HttpServletResponse response) {
        cartService.clearCart(response);
        return "redirect:/user/product/cart";
    }

}
