package com.yanjing.controller;

import com.yanjing.annotation.CurrentCart;
import com.yanjing.entity.Cart;
import com.yanjing.entity.CartItem;
import com.yanjing.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("cart")
public class CartController {
    @Autowired
    private CartService cartService;

    @PostMapping("/item")
    public CartItem addToCart(@RequestParam Integer bookId, @CurrentCart Cart cart) {
        CartItem cartItem = cartService.createCartItem(bookId, 1);
        cart.updateCart(cartItem);
        return cartItem;
    }
}
