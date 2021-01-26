package com.yanjing.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Cart implements Serializable {
    private List<CartItem> cartItems = new ArrayList<>();

    public Cart() {
    }

    public CartItem getCartItemByBookId(Integer id) {
        return cartItems.stream().filter(cartItem -> cartItem.getBookId().equals(id)).findFirst().orElse(null);
    }

    public void updateCart(CartItem cartItem) {
        CartItem foundItem = getCartItemByBookId(cartItem.getBookId());
        if (foundItem == null) {
            cartItems.add(cartItem);
            return;
        }
        foundItem.setQuantity(foundItem.getQuantity() + cartItem.getQuantity());
    }

    public Double getTotalPrice() {
        return cartItems.stream().mapToDouble(CartItem :: getSubTotal).sum();
    }
}
