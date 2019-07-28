package com.cplh.springboot.security.core.validate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.ServletWebRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

/**
 * 验证码控制器
 */
@RestController
public class ValidateCodeController {

    @Autowired
    Map<String, ValidateCodeProcessor> validateCodeProcessorsMap;

    /**
     * 获取验证码，根据验证码类型调用 ValidateCodeProcessor 的不同实现
     * @param request
     * @param response
     * @throws IOException
     */
    @GetMapping("/code/{type}")
    public void createCode(HttpServletRequest request, HttpServletResponse response,
                           @PathVariable String type) throws Exception {
        validateCodeProcessorsMap.get(type + "CodeProcessor").create(new ServletWebRequest(request, response));

    }
}
