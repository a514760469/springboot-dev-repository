package com.cplh.springboot.security.web.rest;

import com.cplh.springboot.security.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.connect.web.ProviderSignInUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.ServletWebRequest;

import javax.servlet.http.HttpServletRequest;

@RestController
public class RegisterController {

    @Autowired
    ProviderSignInUtils providerSignInUtils;

    @PostMapping("/user/regist")
    public User regist(User user, HttpServletRequest request) {
        // 不管注册用户 还是绑定用户都会拿到一个用户唯一标识
        String userId = user.getUsername();
        providerSignInUtils.doPostSignUp(userId, new ServletWebRequest(request));
        return user;
    }

}
