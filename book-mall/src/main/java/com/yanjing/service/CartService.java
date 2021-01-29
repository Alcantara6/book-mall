package com.yanjing.service;

import com.yanjing.entity.Cart;
import com.yanjing.entity.CartItem;

public interface CartService {
    CartItem createCartItem(Integer bookId, Integer quantity);

    Cart deleteCartItem(Integer bookId, Cart cart);
}
