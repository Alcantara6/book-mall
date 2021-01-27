package com.yanjing.service;

import com.yanjing.assistant.CartManager;
import com.yanjing.assistant.UserManager;
import com.yanjing.entity.User;
import com.yanjing.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
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
    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public User login(User user) {
        User registeredUser = findByUsername(user.getUsername()).orElse(null);
        userManager.login(user);
        cartManager.initCart();
        return registeredUser;
    }
}
