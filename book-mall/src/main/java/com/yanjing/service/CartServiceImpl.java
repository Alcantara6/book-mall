package com.yanjing.service;

import com.yanjing.assistant.CartManager;
import com.yanjing.entity.Book;
import com.yanjing.entity.Cart;
import com.yanjing.entity.CartItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author yanjing
 * @date 2021-01-25
 */
@Service
public class CartServiceImpl implements CartService {
    @Autowired
    private BookService bookService;

    @Autowired
    private CartManager cartManager;

    @Override
    public CartItem createCartItem(Integer bookId, Integer quantity) {
        Book book = bookService.findById(bookId).get();
        return CartItem.builder().book(book).quantity(quantity).build();
    }

    @Override
    public Cart deleteCartItem(Integer bookId, Cart cart) {
        cart.removeItem(bookId);
        cartManager.updateCart(cart);
        return cart;
    }
}
