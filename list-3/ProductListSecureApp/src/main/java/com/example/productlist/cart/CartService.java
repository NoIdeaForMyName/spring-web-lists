package com.example.productlist.cart;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

@Service
public class CartService {

    private static final String CART_COOKIE_NAME = "cart";
    private static final int CART_COOKIE_MAX_AGE = 604800;
    private final ObjectMapper objectMapper;

    public CartService(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    public Cart getCartFromCookies(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (CART_COOKIE_NAME.equals(cookie.getName())) {
                    String encodedJson = cookie.getValue();
                    try {
                        String json = URLDecoder.decode(encodedJson, StandardCharsets.UTF_8.toString());
                        return objectMapper.readValue(json, Cart.class);
                    } catch (JsonProcessingException | UnsupportedEncodingException e) {
                        e.printStackTrace();
                        return new Cart();
                    }
                }
            }
        }
        return new Cart();
    }

    public void saveCartToCookies(HttpServletResponse response, Cart cart) {
        try {
            String json = objectMapper.writeValueAsString(cart);
            String encodedJson = URLEncoder.encode(json, StandardCharsets.UTF_8.toString());
            Cookie cookie = new Cookie(CART_COOKIE_NAME, encodedJson);
            cookie.setPath("/");
            cookie.setHttpOnly(true);
            cookie.setMaxAge(CART_COOKIE_MAX_AGE);
            response.addCookie(cookie);
        } catch (JsonProcessingException | UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    public void clearCart(HttpServletResponse response) {
        Cookie cookie = new Cookie(CART_COOKIE_NAME, null);
        cookie.setPath("/");
        cookie.setHttpOnly(true);
        cookie.setMaxAge(0);
        response.addCookie(cookie);
    }
}
