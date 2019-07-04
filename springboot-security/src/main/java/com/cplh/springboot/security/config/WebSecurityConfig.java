package com.cplh.springboot.security.config;

import com.cplh.springboot.security.authentication.AppAuthenticationFailureHandler;
import com.cplh.springboot.security.authentication.AppAuthenticationSuccessHandler;
import com.cplh.springboot.security.core.properties.SecurityProperties;
import com.cplh.springboot.security.core.validate.ValidateCodeFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    SecurityProperties securityProperties;

    @Autowired
    AppAuthenticationSuccessHandler appAuthenticationSuccessHandler;

    @Autowired
    AppAuthenticationFailureHandler appAuthenticationFailureHandler;

    @Autowired
    ValidateCodeFilter validateCodeFilter;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
//        http.httpBasic() // BasicAuthenticationFilter 生效

        http.addFilterBefore(validateCodeFilter, UsernamePasswordAuthenticationFilter.class)
            .formLogin()
            .loginPage("/authentication/require")       // 自定义的登录页面 signIn.html
//            .loginPage(securityProperties.getBrowser().getLoginPage())
            .loginProcessingUrl("/authentication/form") // 自定义表单登录请求 action="/authentication/form"
            .successHandler(appAuthenticationSuccessHandler)
            .failureHandler(appAuthenticationFailureHandler)

            .and()
            .authorizeRequests()
            .antMatchers("/code/image", "/authentication/require", securityProperties.getBrowser().getLoginPage()).permitAll()
            .anyRequest().authenticated()
            .and()
            .csrf().disable();
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
