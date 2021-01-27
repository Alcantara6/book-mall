package com.yanjing.service;

import com.yanjing.assistant.CartManager;
import com.yanjing.assistant.UserManager;
import com.yanjing.entity.User;
import com.yanjing.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserManager userManager;

    @Autowired
    private CartManager cartManager;

    @Override
    public Optional<User> findById(Integer id) {
        return userRepository.findById(id);
    }

    @Override
    public Optional<User> findByUserName(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public User login(User user) {
        User registeredUser = findByUserName(user.getUsername()).orElse(null);
        // TODO: 是否还需检查null
        if (registeredUser == null) {
            return null;
        }
        userManager.login(user);
        cartManager.initCart();
        return registeredUser;
    }
}
