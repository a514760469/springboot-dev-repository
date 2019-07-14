package com.cplh.springboot.security.config;

import com.cplh.springboot.security.authentication.AppAuthenticationFailureHandler;
import com.cplh.springboot.security.authentication.AppAuthenticationSuccessHandler;
import com.cplh.springboot.security.core.authentication.mobile.SmsCodeAuthenticationFilter;
import com.cplh.springboot.security.core.authentication.mobile.SmsCodeAuthenticationProvider;
import com.cplh.springboot.security.core.authentication.mobile.SmsCodeAuthenticationSecurityConfig;
import com.cplh.springboot.security.core.properties.SecurityProperties;
import com.cplh.springboot.security.core.validate.SmsCodeFilter;
import com.cplh.springboot.security.core.validate.ValidateCodeFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

import javax.sql.DataSource;

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

    @Autowired
    DataSource dataSource;

    @Autowired
    MyUserDetailService userDetailsService;

    // sms
//    @Autowired
//    SmsCodeAuthenticationFilter smsCodeAuthenticationFilter;

    @Autowired
    SmsCodeFilter smsCodeFilter;

    @Autowired
    SmsCodeAuthenticationProvider smsCodeAuthenticationProvider;

    @Autowired
    SmsCodeAuthenticationSecurityConfig smsCodeAuthenticationSecurityConfig;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
//        http.httpBasic() // BasicAuthenticationFilter 生效

        http.addFilterBefore(smsCodeFilter, UsernamePasswordAuthenticationFilter.class)
            .addFilterBefore(validateCodeFilter, UsernamePasswordAuthenticationFilter.class)
            .formLogin()
                .loginPage("/authentication/require")       // 自定义的登录页面 signIn.html
    //            .loginPage(securityProperties.getBrowser().getLoginPage())
                .loginProcessingUrl("/authentication/form") // 自定义表单登录请求 action="/authentication/form"
                .successHandler(appAuthenticationSuccessHandler)
                .failureHandler(appAuthenticationFailureHandler)
                .and()
             // remember-me 功能
            .rememberMe()
                .tokenRepository(persistentTokenRepository())
                .tokenValiditySeconds(securityProperties.getBrowser().getRememberMeSeconds())
                .userDetailsService(userDetailsService)


            .and()
            .authorizeRequests()
            .antMatchers("/code/*", "/authentication/require", securityProperties.getBrowser().getLoginPage()).permitAll()
            .anyRequest().authenticated()
            .and()
            .csrf().disable()

                // smsCodeAuthenticationSecurityConfig配置类中的所有配置加进来
            .apply(smsCodeAuthenticationSecurityConfig);
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * 持久化token
     * @return
     */
    @Bean
    public PersistentTokenRepository persistentTokenRepository() {
        // 用户登录后将token保存到数据库里
        JdbcTokenRepositoryImpl tokenRepository = new JdbcTokenRepositoryImpl();
        tokenRepository.setDataSource(dataSource);
//        tokenRepository.setCreateTableOnStartup(true);
        return tokenRepository;
    }

}

