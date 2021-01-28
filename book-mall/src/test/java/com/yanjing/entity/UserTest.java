package com.yanjing.entity;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.Set;

public class UserTest {

    private Validator validator;

    @BeforeEach
    void setupValidatorInstance() {
        validator = Validation.buildDefaultValidatorFactory().getValidator();
    }

    @Nested
    class UserIdTest {
        @Test
        public void getId_null_ifNotSet() {
            User user = new User();

            Integer id = user.getId();

            assertNull(id);
        }

        @Test
        public void getId_happyPath() {
            User user = new User();
            user.setId(1);

            Integer id = user.getId();

            assertEquals(1, id);
        }
    }

    @Nested
    class UsernameTest {
        @Test
        public void setUsername_validateFail_ifSetNull() {
            User user = User.builder().username(null).password("123").build();

            Set<ConstraintViolation<User>> violations = validator.validate(user);

            assertEquals(1, violations.size());
        }

        @Test
        public void setUsername_validateSuccess_ifSetEmptyString() {
            User user = User.builder().username("").password("123").build();

            Set<ConstraintViolation<User>> violations = validator.validate(user);

            assertEquals(0, violations.size());
        }

        @Test
        public void setUsername_validateSuccess_ifSetNoneEmptyString() {
            User user = User.builder().username("admin").password("123").build();

            Set<ConstraintViolation<User>> violations = validator.validate(user);

            assertEquals(0, violations.size());
        }

        @Test
        public void getUsername_happyPath() {
            User user = new User();
            user.setUsername("admin");

            String username = user.getUsername();

            assertEquals("admin", username);
        }
    }

    @Nested
    class UserPasswordTest {
        @Test
        public void setUserPassword_validateFail_ifSetNull() {
            User user = User.builder().username("admin").password(null).build();

            Set<ConstraintViolation<User>> violations = validator.validate(user);

            assertEquals(1, violations.size());
        }

        @Test
        public void setUserPassword_validateSuccess_ifSetNoneEmptyString() {
            User user = User.builder().username("admin").password("123").build();

            Set<ConstraintViolation<User>> violations = validator.validate(user);

            assertEquals(0, violations.size());
        }

        @Test
        public void getUserPassword_happyPath() {
            User user = new User();
            user.setPassword("123");

            String password = user.getPassword();

            assertEquals("123", password);
        }
    }

    @Nested
    class UserEmailTest {
        @Test
        public void getEmail_null_ifNotSet() {
            User user = new User();

            String email = user.getEmail();

            assertNull(email);
        }

        @Test
        public void setEmail_validateFail_ifPatternNotMatch() {
            User user = User.builder().username("admin").password("123").email("yj.com").build();

            Set<ConstraintViolation<User>> violations = validator.validate(user);

            assertEquals(1, violations.size());
        }

        @Test
        public void setEmail_validateSuccess_ifPatternMatch() {
            User user = User.builder().username("admin").password("123").email("yj@163.com").build();

            Set<ConstraintViolation<User>> violations = validator.validate(user);

            assertEquals(0, violations.size());
        }

        @Test
        public void getEmail_happyPath() {
            User user = new User();
            user.setEmail("yj@deepdraw.cn");

            String email = user.getEmail();

            assertEquals("yj@deepdraw.cn", email);
        }
    }
}
