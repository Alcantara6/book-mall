package com.yanjing.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Cart implements Serializable {
    private User user;
    private List<CartItem> cartItems = new ArrayList<>();

    public Cart() {
    }

    public Cart(User user) {
        this.user = user;
    }

    public CartItem findCartItemByBookId(Integer id) {
        return cartItems.stream().filter(cartItem -> cartItem.getBookId().equals(id)).findFirst().orElse(null);
    }

    public void updateCart(CartItem cartItem) {
        CartItem foundItem = findCartItemByBookId(cartItem.getBookId());
        if (foundItem == null) {
            cartItems.add(cartItem);
        } else {
            foundItem.setQuantity(foundItem.getQuantity() + cartItem.getQuantity());
        }
    }

    // 函数名字可以是以下几种形式：动宾词组（动作）、名词（往往是属性）、形容词词组（往往是状态）
    public Double totalPrice() {
        return cartItems.stream().mapToDouble(CartItem :: getSubTotal).sum();
    }
}
