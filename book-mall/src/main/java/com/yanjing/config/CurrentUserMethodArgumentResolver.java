package com.yanjing.config;

import com.yanjing.annotation.CurrentUser;
import com.yanjing.assistant.UserManager;
import com.yanjing.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

@Component("UserMethodArgumentResolver")
public class CurrentUserMethodArgumentResolver implements HandlerMethodArgumentResolver {
    @Autowired
    private UserManager userManager;

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.getParameterType().isAssignableFrom(User.class) && parameter.hasParameterAnnotation(CurrentUser.class);
    }

    @Override
    public Object resolveArgument(
        MethodParameter parameter,
        ModelAndViewContainer modelAndViewContainer,
        NativeWebRequest webRequest,
        WebDataBinderFactory binderFactory
    ) {
        // 获取httpSession的另一种方式
        // HttpSession httpSession = nativeWebRequest.getNativeRequest(HttpServletRequest.class).getSession();
        return userManager.getCurrentUser();
    }
}
