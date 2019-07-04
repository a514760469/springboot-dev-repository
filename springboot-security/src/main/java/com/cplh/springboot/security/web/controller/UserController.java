package com.cplh.springboot.security.web.controller;

import com.cplh.springboot.security.entity.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class UserController {

    private Logger logger = LoggerFactory.getLogger(getClass());



    @GetMapping("/user")
    public List<User> userList() {
        logger.info("userList...");
        ArrayList<User> users = new ArrayList<>();
        users.add(new User());
        users.add(new User());
        users.add(new User());
        return users;
    }

    @GetMapping("/user/{id:.+//}")
    public User user(@PathVariable String id) {
        logger.info("user...");
        return new User();
    }

//    @GetMapping("/me")
//    public Object getCurrentUser() {
//        return SecurityContextHolder.getContext().getAuthentication();
//    }

//    @GetMapping("/me")
//    public Object getCurrentUser(Authentication authentication) {
//        return authentication;
//    }

    @GetMapping("/me")
    public Object getCurrentUser(@AuthenticationPrincipal UserDetails user) {
        return user;
    }

}
