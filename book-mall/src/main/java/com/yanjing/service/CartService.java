package com.yanjing.service;

import com.yanjing.entity.CartItem;

public interface CartService {
    CartItem createCartItem(Integer bookId, Integer quantity);
}
