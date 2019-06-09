package com.gds.controller;

import com.gds.exception.UserNotFoundException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.sql.SQLOutput;

@RestController
public class HelloController {

    @RequestMapping("/hi")
    public String hi(@RequestParam String user) {
        System.out.println("11111111");
        if(user.equals("aaa")) {
            throw new UserNotFoundException();
        }
        return "hello world";
    }
    /**
     *
     * @return
     */
    @GetMapping("/hello")
    public Mono<String> hello() {
        return  Mono.just("welcome to reactive world ~~");
    }

}
