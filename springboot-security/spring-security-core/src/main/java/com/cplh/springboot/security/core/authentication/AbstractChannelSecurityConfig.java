package com.cplh.springboot.security.core.authentication;

import com.cplh.springboot.security.core.properties.constant.SecurityConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

/**
 * 抽象出公共配置
 */
public class AbstractChannelSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    protected AuthenticationSuccessHandler appAuthenticationSuccessHandler;

    @Autowired
    protected AuthenticationFailureHandler appAuthenticationFailureHandler;


    protected void applyPasswordAuthenticationConfig(HttpSecurity http) throws Exception {

        http.formLogin()
            .loginPage(SecurityConstants.DEFAULT_UNAUTHENTICATION_URL)      // 表单登录的登录页
            .loginProcessingUrl(SecurityConstants.DEFAULT_LOGIN_PROCESSING_URL_FORM)
            .successHandler(appAuthenticationSuccessHandler)
            .failureHandler(appAuthenticationFailureHandler);
    }

}
