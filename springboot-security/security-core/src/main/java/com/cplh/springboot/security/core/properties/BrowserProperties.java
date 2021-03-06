package com.cplh.springboot.security.core.properties;

import com.cplh.springboot.security.core.properties.constant.SecurityConstants;
import lombok.Data;

@Data
public class BrowserProperties {

    private SessionProperties session = new SessionProperties();

    /**
     * 注册页
     */
    private String signUpUrl = "/signUp.html";

    private String signOutUrl;

    private String loginPage = SecurityConstants.DEFAULT_LOGIN_PAGE_URL;

    private LoginType loginType = LoginType.JSON;

    private int rememberMeSeconds = 3600;// 一小时

}
