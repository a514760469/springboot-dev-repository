package com.cplh.springboot.security.browser;

import com.cplh.springboot.security.browser.logout.BrowserLogoutSuccessHandler;
import com.cplh.springboot.security.browser.session.AppExpiredSessionStrategy;
import com.cplh.springboot.security.browser.session.AppInvalidSessionStrategy;
import com.cplh.springboot.security.core.properties.SecurityProperties;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.security.web.session.InvalidSessionStrategy;
import org.springframework.security.web.session.SessionInformationExpiredStrategy;

@Configuration
public class BrowserSecurityBeanConfig {

    @Autowired
    private SecurityProperties securityProperties;

    @Bean
    @ConditionalOnMissingBean(InvalidSessionStrategy.class)
    public InvalidSessionStrategy invalidSessionStrategy() {
        return new AppInvalidSessionStrategy(
                securityProperties.getBrowser().getSession().getSessionInvalidUrl());
    }

    @Bean
    @ConditionalOnMissingBean(SessionInformationExpiredStrategy.class)
    public SessionInformationExpiredStrategy sessionInformationExpiredStrategy() {
        return new AppExpiredSessionStrategy(
                securityProperties.getBrowser().getSession().getSessionInvalidUrl());
    }

    @Bean
    @ConditionalOnMissingBean(LogoutSuccessHandler.class)
    public LogoutSuccessHandler logoutSuccessHandler(ObjectMapper objectMapper) {
        return new BrowserLogoutSuccessHandler(securityProperties.getBrowser().getSignOutUrl(), objectMapper);
    }


}
