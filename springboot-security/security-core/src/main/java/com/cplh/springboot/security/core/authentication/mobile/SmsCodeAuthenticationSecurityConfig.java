package com.cplh.springboot.security.core.authentication.mobile;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;

/**
 * 短信验证码配置
 */
@Component
public class SmsCodeAuthenticationSecurityConfig
        extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> {

    /**
     * 登录成功处理器
     */
    @Autowired
    AuthenticationSuccessHandler appAuthenticationSuccessHandler;

    /**
     * 登录失败处理器
     */
    @Autowired
    AuthenticationFailureHandler appAuthenticationFailureHandler;

    /**
     * 短信provider
     */
//    @Autowired
//    SmsCodeAuthenticationProvider smsCodeAuthenticationProvider;

    @Autowired
    private UserDetailsService userDetailsService;

    @Override
    public void configure(HttpSecurity http) throws Exception {
        SmsCodeAuthenticationFilter smsCodeAuthenticationFilter = new SmsCodeAuthenticationFilter();
        smsCodeAuthenticationFilter.setAuthenticationManager(http.getSharedObject(AuthenticationManager.class));
        smsCodeAuthenticationFilter.setAuthenticationSuccessHandler(appAuthenticationSuccessHandler);
        smsCodeAuthenticationFilter.setAuthenticationFailureHandler(appAuthenticationFailureHandler);

        SmsCodeAuthenticationProvider provider = new SmsCodeAuthenticationProvider();
        provider.setUserDetailsService(userDetailsService);

        http
            .authenticationProvider(provider)
            .addFilterAfter(smsCodeAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

    }

}
