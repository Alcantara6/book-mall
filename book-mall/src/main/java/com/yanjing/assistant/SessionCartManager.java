package com.yanjing.assistant;

import com.yanjing.entity.Cart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpSession;

@Component
public class SessionCartManager implements CartManager {
    private static final String SESSION_CART = "cart";

    @Autowired
    private HttpSession session;

    @Override
    public void initCart() {
        setAttribute(SESSION_CART, new Cart());
    }

    @Override
    public Cart getCurrentCart() {
        return (Cart) session.getAttribute(SESSION_CART);
    }

    @Override
    public void updateCart(Cart cart) {
        setAttribute(SESSION_CART, cart);
    }

    private void setAttribute(String name, Object value) {
        if (session != null) {
            session.setAttribute(name, value);
        }
    }
}
