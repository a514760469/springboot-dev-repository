package com.cplh.security;

import com.cplh.springboot.security.core.authorize.AuthorizeConfigProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;
import org.springframework.stereotype.Component;

/**
 * web项目配置，一些路径的权限
 */
@Component
public class WebDemoAuthorizeConfigProvider implements AuthorizeConfigProvider {

    @Override
    public void config(ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry config) {
        config
                .antMatchers("/user").hasRole("ADMIN")
                .antMatchers("/demo.html").hasRole("XXX")
                .antMatchers("/social/user/image").permitAll();
    }
}
