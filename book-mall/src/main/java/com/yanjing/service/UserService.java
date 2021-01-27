package com.yanjing.service;

import com.yanjing.entity.User;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * @author yanjing
 * @date 2021-01-27
 */
@Service
public interface UserService {
    Optional<User> findById(Integer id);

    Optional<User> findByUserName(String username);

    User login(User user);
}
