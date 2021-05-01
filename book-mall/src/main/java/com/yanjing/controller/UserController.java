package com.yanjing.controller;

import com.yanjing.annotation.CurrentUser;
import com.yanjing.entity.User;
import com.yanjing.service.UserService;
import com.yanjing.util.response.Response;
import com.yanjing.util.response.ResponseUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("user")
public class UserController {
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);
    @Autowired
    private UserService userService;

    @PostMapping("login")
    public Response<User> login(@RequestBody User user) {
        String requestUsername = user.getUsername();
        if (requestUsername == null) {
            logger.error("用户名不能为空");
            return ResponseUtils.badRequest("用户名不能为空");
        }
        User registeredUser = userService.findByUsername(requestUsername).orElse(null);
        if (registeredUser == null) {
            logger.error("用户不存在");
            return ResponseUtils.badRequest("用户不存在");
        }
        if (!registeredUser.getPassword().equals(user.getPassword())) {
            logger.error("密码不正确，正确密码应为{}", registeredUser.getPassword());
            return ResponseUtils.badRequest("密码不正确");
        }
        userService.login(registeredUser);
        return ResponseUtils.success(createUserInfo(registeredUser));
    }

    // 如果sessionUser已过期，会被拦截器拦截，这里返回的肯定是有效的user
    @GetMapping("info")
    public Response<User> getUserInfo(@CurrentUser User user) {
        return ResponseUtils.success(createUserInfo(user));
    }

    private User createUserInfo(User currentUser) {
        User user = new User();
        if (currentUser != null) {
            user.setUsername(currentUser.getUsername());
            user.setEmail(currentUser.getEmail());
        }
        return user;
    }
}
