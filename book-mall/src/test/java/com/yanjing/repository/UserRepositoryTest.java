package com.yanjing.repository;

import com.yanjing.entity.User;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class UserRepositoryTest {
    @Autowired
    private UserRepository userRepository;

    @Test
    public void findByUsername_shouldReturnNull_ifUserDoesNotExist() {
        insertIntoTestUser("abc");

        User user = userRepository.findByUsername("def").orElse(null);

        assertNull(user);
    }

    @Test
    public void findByUsername_happyPath() {
        User createdUser = insertIntoTestUser("ghk");

        User foundUser = userRepository.findByUsername("ghk").orElse(null);

        assertEquals(createdUser, foundUser);
    }

    private User insertIntoTestUser(String username) {
        User user = User.builder().username(username).password("123").build();
        userRepository.saveAndFlush(user);
        return user;
    }
}
