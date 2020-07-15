package com.cplh.springboot.app.config;

import com.cplh.springboot.security.core.authentication.AbstractChannelSecurityConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;

@Configuration
public class WebSecurityConfig extends AbstractChannelSecurityConfig {

    /**
     * 不这么干无法注入AuthenticationManager 这个bean
     * @return
     * @throws Exception
     */
    @Bean(BeanIds.AUTHENTICATION_MANAGER)
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

}
