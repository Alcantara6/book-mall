package com.yanjing.assistant;

import com.yanjing.entity.User;

public interface UserManager {
    /**
     * get current user from session
     * @return
     */
    User getCurrentUser();

    /**
     * save current user into session
     * @param user
     */
    void login(User user);
}
