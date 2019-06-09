package com.gds.exception;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class MyExceptionHandler {

    @ExceptionHandler(UserNotFoundException.class)
    public String handleException(Exception e, HttpServletRequest request) {
        Map<String, Object> map = new HashMap<>();
        map.put("message", "用户错误");
        map.put("code", "user.error");
        request.setAttribute("javax.servlet.error.message", map);
        request.setAttribute("ext", map);
        // 传入我们自己的状态码，否则就不会进入定制错误页面解析流程
        request.setAttribute("javax.servlet.error.status_code", 500);
        // 转发到/error
        return "forward:/error";
    }
}
