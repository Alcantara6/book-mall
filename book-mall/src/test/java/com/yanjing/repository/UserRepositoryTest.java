package com.yanjing.repository;

import com.yanjing.entity.User;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Rollback
@Transactional
public class UserRepositoryTest {
    @Autowired
    private UserRepository userRepository;

    @Test
    public void findByUsername_shouldReturnNull_ifUserDoesNotExist() {
        createUser("abc");

        User user = userRepository.findByUsername("def").orElse(null);

        assertNull(user);
    }

    @Test
    public void findByUsername_happyPath() {
        User createdUser = createUser("ghk");

        User foundUser = userRepository.findByUsername("ghk").orElse(null);

        assertEquals(createdUser, foundUser);
    }

    private User createUser(String username) {
        User user = User.builder().username(username).password("123").build();
        userRepository.saveAndFlush(user);
        return user;
    }
}
