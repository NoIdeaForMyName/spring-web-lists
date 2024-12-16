package com.example.productlist.cart;

import com.example.productlist.entity.Product;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class CartProduct {

    private final Product product;
    private final int quantity;

}
