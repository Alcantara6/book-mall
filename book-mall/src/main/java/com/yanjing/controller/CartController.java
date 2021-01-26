package com.yanjing.controller;

import com.yanjing.annotation.CurrentCart;
import com.yanjing.entity.Cart;
import com.yanjing.entity.CartItem;
import com.yanjing.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("cart")
public class CartController {
    @Autowired
    private CartService cartService;

    @GetMapping("add")
    public CartItem addToCart(@RequestParam("bookId") Integer bookId, @CurrentCart Cart cart) {
        CartItem cartItem = cartService.createCartItem(bookId, 1);
        cart.updateCart(cartItem);
        return cartItem;
    }
}
