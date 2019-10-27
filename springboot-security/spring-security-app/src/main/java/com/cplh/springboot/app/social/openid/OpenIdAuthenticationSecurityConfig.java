package com.cplh.springboot.app.social.openid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.social.connect.UsersConnectionRepository;
import org.springframework.social.security.SocialUserDetailsService;
import org.springframework.stereotype.Component;

@Component
public class OpenIdAuthenticationSecurityConfig extends
        SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> {

    /**
     * 成功处理器
     */
    @Autowired
    private AuthenticationSuccessHandler appAuthenticationSuccessHandler;

    /**
     * 失败处理器
     */
    @Autowired
    private AuthenticationFailureHandler appAuthenticationFailureHandler;

    @Autowired
    private SocialUserDetailsService userDetailsService;

    @Autowired
    private UsersConnectionRepository usersConnectionRepository;

    @Override
    public void configure(HttpSecurity http) {
        OpenIdAuthenticationFilter openIdAuthenticationFilter = new OpenIdAuthenticationFilter();
        openIdAuthenticationFilter.setAuthenticationManager(http.getSharedObject(AuthenticationManager.class));
        openIdAuthenticationFilter.setAuthenticationSuccessHandler(appAuthenticationSuccessHandler);
        openIdAuthenticationFilter.setAuthenticationFailureHandler(appAuthenticationFailureHandler);

        OpenIdAuthenticationProvider provider = new OpenIdAuthenticationProvider();
        provider.setUserDetailsService(userDetailsService);
        provider.setUsersConnectionRepository(usersConnectionRepository);

        http.authenticationProvider(provider)
            .addFilterAfter(openIdAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

    }
}
