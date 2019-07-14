package com.cplh.springboot.security.core.validate;

import com.cplh.springboot.security.core.properties.SecurityProperties;
import com.cplh.springboot.security.core.validate.ImageCode;
import com.cplh.springboot.security.core.validate.ValidateCodeGenerator;
import com.cplh.springboot.security.core.validate.sms.SmsCodeSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.connect.web.HttpSessionSessionStrategy;
import org.springframework.social.connect.web.SessionStrategy;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.ServletWebRequest;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

/**
 * 验证码控制器
 */
@RestController
public class ValidateCodeController {

    public static final String SESSION_KEY = "SESSION_KEY_IMAGE_CODE";

    private SessionStrategy sessionStrategy = new HttpSessionSessionStrategy();

    @Autowired
    private ValidateCodeGenerator validateCodeGenerator;

    @Autowired
    private ValidateCodeGenerator smsCodeGenerator;

    /**
     * 短信发送器
     */
    @Autowired
    private SmsCodeSender smsCodeSender;


    /**
     * 图形验证码
     * @param request
     * @param response
     * @throws IOException
     */
    @GetMapping("/code/image")
    public void createCode(HttpServletRequest request, HttpServletResponse response) throws IOException {
        ImageCode imageCode = (ImageCode) validateCodeGenerator.generate(request);
        sessionStrategy.setAttribute(new ServletWebRequest(request), SESSION_KEY + "IMAGE", imageCode);
        ImageIO.write(imageCode.getBufferedImage(), "JPEG", response.getOutputStream());
    }

    /**
     * 短信验证码
     * @param request
     * @param response
     * @throws IOException
     */
    @GetMapping("/code/sms")
    public void createSmsCode(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletRequestBindingException {
        ValidateCode smsCode = smsCodeGenerator.generate(request);
        sessionStrategy.setAttribute(new ServletWebRequest(request), SESSION_KEY + "SMS", smsCode);
        String mobile = ServletRequestUtils.getRequiredStringParameter(request, "mobile");
        smsCodeSender.send(mobile, smsCode.getCode());

    }


}
