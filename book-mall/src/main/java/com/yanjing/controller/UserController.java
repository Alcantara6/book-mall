package com.yanjing.controller;

import com.yanjing.entity.User;
import com.yanjing.service.UserService;
import com.yanjing.util.response.Response;
import com.yanjing.util.response.ResponseUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("user")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("login")
    public Response<User> login(@RequestBody User user) {
        String requestUsername = user.getUsername();
        if (requestUsername == null) {
            return ResponseUtils.badRequest("用户名不能为空");
        }
        User registeredUser = userService.findByUsername(requestUsername).orElse(null);
        if (registeredUser == null) {
            return ResponseUtils.badRequest("用户不存在");
        }
        if (!registeredUser.getPassword().equals(user.getPassword())) {
            return ResponseUtils.badRequest("密码不正确");
        }
        userService.login(registeredUser);
        return ResponseUtils.success(registeredUser);
    }
}
