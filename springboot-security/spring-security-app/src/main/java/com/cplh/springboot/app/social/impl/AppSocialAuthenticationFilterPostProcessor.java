package com.cplh.springboot.app.social.impl;

import com.cplh.springboot.security.core.social.SocialAuthenticationFilterPostProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.social.security.SocialAuthenticationFilter;
import org.springframework.stereotype.Component;

@Component
public class AppSocialAuthenticationFilterPostProcessor implements SocialAuthenticationFilterPostProcessor {

    @Autowired
    AuthenticationSuccessHandler appAuthenticationSuccessHandler;

    @Override
    public void process(SocialAuthenticationFilter socialAuthenticationFilter) {
        socialAuthenticationFilter.setAuthenticationSuccessHandler(appAuthenticationSuccessHandler);
    }
}
