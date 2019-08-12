package com.cplh.springboot.security.config;

import com.cplh.springboot.security.authentication.AppAuthenticationFailureHandler;
import com.cplh.springboot.security.authentication.AppAuthenticationSuccessHandler;
import com.cplh.springboot.security.config.constant.SecurityConstants;
import com.cplh.springboot.security.core.authentication.AbstractChannelSecurityConfig;
import com.cplh.springboot.security.core.authentication.mobile.SmsCodeAuthenticationSecurityConfig;
import com.cplh.springboot.security.core.properties.SecurityProperties;
import com.cplh.springboot.security.core.validate.ValidateCodeFilter;
import com.cplh.springboot.security.core.validate.ValidateCodeSecurityConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.social.security.SpringSocialConfigurer;

import javax.sql.DataSource;

/**
 * 浏览器Security配置
 */
@Configuration
public class WebSecurityConfig extends AbstractChannelSecurityConfig {

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

    /**
     * 抽象配置
     */
    @Autowired
    SmsCodeAuthenticationSecurityConfig smsCodeAuthenticationSecurityConfig;

    @Autowired
    ValidateCodeSecurityConfig validateCodeSecurityConfig;

    /**
     * 社交登录配置
     * 过滤器链上增加相应的过滤器
     * 默认拦截 /auth/
     *  /auth/{providerId}
     */
    @Autowired
    SpringSocialConfigurer springSocialConfigurer;


    @Override
    protected void configure(HttpSecurity http) throws Exception {

        // 抽象配置生效
        applyPasswordAuthenticationConfig(http);

        // smsCodeAuthenticationSecurityConfig配置类中的所有配置加进来
        http.apply(smsCodeAuthenticationSecurityConfig)
                .and()
            .apply(validateCodeSecurityConfig)
                .and()
            .apply(springSocialConfigurer)
                .and()
            // 浏览器特有配置
            // remember-me 功能
            .rememberMe()
                .tokenRepository(persistentTokenRepository())
                .tokenValiditySeconds(securityProperties.getBrowser().getRememberMeSeconds())
                .userDetailsService(userDetailsService)
                .and()
            .authorizeRequests()
                .antMatchers(
                        SecurityConstants.DEFAULT_UNAUTHENTICATION_URL,
                        SecurityConstants.DEFAULT_LOGIN_PROCESSING_URL_MOBILE,
                        SecurityConstants.DEFAULT_VALIDATE_CODE_URL_PREFIX + "/*",
                        securityProperties.getBrowser().getLoginPage(),
                        securityProperties.getBrowser().getSignUpUrl(),
                        "/user/regist"
                    )
                    .permitAll()
                .anyRequest().authenticated()
                .and()
            .csrf().disable();
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

