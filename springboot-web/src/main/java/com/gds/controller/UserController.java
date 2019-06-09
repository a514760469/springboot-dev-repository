package com.gds.controller;

import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.Map;

@Controller
@RequestMapping("/user")
public class UserController {

    @PostMapping("/login")
    public String login (HttpSession session,
                         @RequestParam("username") String username,
                         @RequestParam("password") String password, Map<String, String> result) {
        if(!StringUtils.isEmpty(username)&& "123456".equals(password)) {
            // 防止重复提交
            session.setAttribute("loginUser", username);
            return "redirect:/main.html";
        } else {
            result.put("message", "用户名密码错误");
            return "login";
        }

    }

}
