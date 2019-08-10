package com.cplh.springboot.security.core.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@ConfigurationProperties(prefix = "application.security", ignoreInvalidFields = true)
public class SecurityProperties {

    // 浏览器配置
    private BrowserProperties browser = new BrowserProperties();

    // 验证码配置
    private ValidateCodeProperties code = new ValidateCodeProperties();

    // 社交相关配置
    private SocialProperties social = new SocialProperties();

    public SocialProperties getSocial() {
        return social;
    }

    public void setSocial(SocialProperties social) {
        this.social = social;
    }

    public BrowserProperties getBrowser() {
        return browser;
    }

    public void setBrowser(BrowserProperties browser) {
        this.browser = browser;
    }

    public ValidateCodeProperties getCode() {
        return code;
    }

    public void setCode(ValidateCodeProperties code) {
        this.code = code;
    }

}
