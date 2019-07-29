package com.cplh.springboot.security.core.authentication;

import com.cplh.springboot.security.authentication.AppAuthenticationFailureHandler;
import com.cplh.springboot.security.authentication.AppAuthenticationSuccessHandler;
import com.cplh.springboot.security.config.constant.SecurityConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * 抽象出公共配置
 */
public class AbstractChannelSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    protected AppAuthenticationSuccessHandler appAuthenticationSuccessHandler;

    @Autowired
    protected AppAuthenticationFailureHandler appAuthenticationFailureHandler;


    protected void applyPasswordAuthenticationConfig(HttpSecurity http) throws Exception {

        http.formLogin()
            .loginPage(SecurityConstants.DEFAULT_UNAUTHENTICATION_URL)
            .loginProcessingUrl(SecurityConstants.DEFAULT_LOGIN_PROCESSING_URL_FORM)
            .successHandler(appAuthenticationSuccessHandler)
            .failureHandler(appAuthenticationFailureHandler);
    }

}
