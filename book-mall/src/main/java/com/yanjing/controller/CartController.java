package com.yanjing.controller;

import com.yanjing.annotation.CurrentCart;
import com.yanjing.entity.Cart;
import com.yanjing.entity.CartItem;
import com.yanjing.service.CartService;
import com.yanjing.util.response.Response;
import com.yanjing.util.response.ResponseUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("cart")
public class CartController {
    @Autowired
    private CartService cartService;

    @GetMapping("/details")
    public Response<Map> getCartDetails(@CurrentCart Cart cart) {
        Map<String, Object> cartDetails = new HashMap<>();
        cartDetails.put("totalPrice", cart.totalPrice());
        cartDetails.put("items", buildCartDetails(cart));
        return ResponseUtils.success(cartDetails);
    }

    @PostMapping("/item")
    public Response<Map> addToCart(@RequestParam Integer bookId, @CurrentCart Cart cart) {
        CartItem cartItem = cartService.createCartItem(bookId, 1);
        cart.updateCart(cartItem);
        return this.getCartDetails(cart);
    }

    @DeleteMapping("item")
    public Response<Map> deleteCartItem(@RequestParam Integer bookId, @CurrentCart Cart cart) {
        Cart updatedCart = cartService.deleteCartItem(bookId, cart);
        return this.getCartDetails(updatedCart);
    }

    private Object[] buildCartDetails(Cart cart) {
        return cart.getCartItems().stream().map(cartItem -> {
            Map<String, Object> cartItemDetail = new HashMap<>();
            cartItemDetail.put("book", cartItem.getBook());
            cartItemDetail.put("quantity", cartItem.getQuantity());
            cartItemDetail.put("subTotal", cartItem.subTotal());
            return cartItemDetail;
        }).toArray();
    }
}
