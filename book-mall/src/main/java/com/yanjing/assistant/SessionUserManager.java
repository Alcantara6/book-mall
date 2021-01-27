package com.yanjing.assistant;

import com.yanjing.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpSession;

@Component
public class SessionUserManager implements UserManager {
    private static final String SESSION_USER = "user";

    @Autowired
    private HttpSession session;

    @Override
    public User getCurrentUser() {
        return (User) session.getAttribute(SESSION_USER);
    }

    @Override
    public void login(User user) {
        if (session != null) {
            session.setAttribute(SESSION_USER, user);
        }
    }
}
