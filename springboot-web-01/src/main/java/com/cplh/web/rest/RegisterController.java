package com.cplh.web.rest;

import com.cplh.dto.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.connect.web.ProviderSignInUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.ServletWebRequest;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@RestController
public class RegisterController {

    @Autowired
    private ProviderSignInUtils providerSignInUtils;

//    @Autowired
//    private AppSignUpUtils appSignUpUtils;

    @PostMapping("/user/regist")
    public User register(User user, HttpServletRequest request) {
        log.info("注册用户。");
        // 不管注册用户 还是绑定用户都会拿到一个用户唯一标识
        String userId = user.getUsername();// 这里以用户名作为userId
        // 注册或绑定的逻辑... 数据库的增删改查而已
        providerSignInUtils.doPostSignUp(userId, new ServletWebRequest(request));
//        appSignUpUtils.doPostSignUp(new ServletWebRequest(request), userId);
        return user;
    }
}
