package com.yanjing.assistant;

import com.yanjing.entity.Cart;

public interface CartManager {
    /**
     * get the current cart from session
     *
     * @return
     */
    Cart getCurrentCart();

    /**
     * create a new cart in session with current user
     */
    void initCart();

    /**
     * update current cart in session
     *
     * @param cart
     */
    void updateCart(Cart cart);
}
