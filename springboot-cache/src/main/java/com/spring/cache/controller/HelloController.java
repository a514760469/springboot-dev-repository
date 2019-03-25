package com.spring.cache.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {

    @RequestMapping("/hello")
    @ResponseBody
    public String helloMvc() {
        return "success";
    }

    @RequestMapping("/hello2")
    public String helloMvc2() {
        return "success";
    }

}
