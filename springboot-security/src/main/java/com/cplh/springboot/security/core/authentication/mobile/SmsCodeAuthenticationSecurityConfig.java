package com.cplh.springboot.security.core.authentication.mobile;

import com.cplh.springboot.security.authentication.AppAuthenticationFailureHandler;
import com.cplh.springboot.security.authentication.AppAuthenticationSuccessHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;

/**
 * 短信验证码配置
 */
@Component
public class SmsCodeAuthenticationSecurityConfig
        extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> {

//    @Autowired
//    SmsCodeAuthenticationFilter smsCodeAuthenticationFilter;

    @Autowired
    AppAuthenticationSuccessHandler appAuthenticationSuccessHandler;

    @Autowired
    AppAuthenticationFailureHandler appAuthenticationFailureHandler;

    @Autowired
    SmsCodeAuthenticationProvider smsCodeAuthenticationProvider;


    @Override
    public void configure(HttpSecurity http) throws Exception {
        SmsCodeAuthenticationFilter smsCodeAuthenticationFilter = new SmsCodeAuthenticationFilter();
        smsCodeAuthenticationFilter.setAuthenticationManager(http.getSharedObject(AuthenticationManager.class));
        smsCodeAuthenticationFilter.setAuthenticationSuccessHandler(appAuthenticationSuccessHandler);
        smsCodeAuthenticationFilter.setAuthenticationFailureHandler(appAuthenticationFailureHandler);

        http.authenticationProvider(smsCodeAuthenticationProvider)
            .addFilterAfter(smsCodeAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

    }

}
