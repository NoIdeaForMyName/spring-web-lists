package com.example.productlist.cart;

import com.example.productlist.entity.Product;

import java.util.ArrayList;
import java.util.List;

public class Cart {
    private List<CartItem> items;
    public Cart() {
        this.items = new ArrayList<>();
    }

    public List<CartItem> getItems() {
        return items;
    }

    public void setItems(List<CartItem> items) {
        this.items = items;
    }

    public void addItem(Long productId) {
        for (CartItem item : items) {
            if (item.getProductId().equals(productId)) {
                item.setQuantity(item.getQuantity() + 1);
                return;
            }
        }
        items.add(new CartItem(productId, 1));
    }

    public void removeItem(Long productId) {
        items.removeIf(item -> item.getProductId().equals(productId));
    }

    public void removeOne(Long productId) {
        for (CartItem item: items) {
            if (item.getProductId().equals(productId)) {
                if (item.getQuantity() > 1)
                    item.setQuantity(item.getQuantity()-1);
                else
                    items.remove(item);
            }
        }
    }

    public void clear() {
        items.clear();
    }
}
