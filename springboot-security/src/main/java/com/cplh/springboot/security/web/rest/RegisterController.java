package com.cplh.springboot.security.web.rest;

import com.cplh.springboot.security.entity.User;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RegisterController {

    @PostMapping("/user/regist")
    public void regist(User user) {
        // 注册用户
    }

}
