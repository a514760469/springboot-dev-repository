package com.cplh.springboot.security.browser;

import com.cplh.springboot.security.core.authentication.AbstractChannelSecurityConfig;
import com.cplh.springboot.security.core.authentication.mobile.SmsCodeAuthenticationSecurityConfig;
import com.cplh.springboot.security.core.properties.SecurityProperties;
import com.cplh.springboot.security.core.properties.constant.SecurityConstants;
import com.cplh.springboot.security.core.validate.ValidateCodeFilter;
import com.cplh.springboot.security.core.validate.ValidateCodeSecurityConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.security.web.session.InvalidSessionStrategy;
import org.springframework.security.web.session.SessionInformationExpiredStrategy;
import org.springframework.social.security.SpringSocialConfigurer;

import javax.sql.DataSource;

/**
 * 浏览器Security配置
 */
@Configuration
public class BrowserSecurityConfig extends AbstractChannelSecurityConfig {

    @Autowired
    SecurityProperties securityProperties;

    @Autowired
    ValidateCodeFilter validateCodeFilter;

    @Autowired
    DataSource dataSource;

    @Autowired
    UserDetailsService userDetailsService;

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

    @Autowired
    SessionInformationExpiredStrategy sessionInformationExpiredStrategy;

    @Autowired
    InvalidSessionStrategy invalidSessionStrategy;

    @Autowired
    LogoutSuccessHandler logoutSuccessHandler;


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
            .sessionManagement()
                .invalidSessionStrategy(invalidSessionStrategy)
                .maximumSessions(securityProperties.getBrowser().getSession().getMaximumSessions()) // session最大数量, 设置为1 后边登录会覆盖前边
                .maxSessionsPreventsLogin(securityProperties.getBrowser().getSession().isMaxSessionsPreventsLogin()) // 当session达到最大数量时阻止登录, 默认是踢掉已登录用户
                .expiredSessionStrategy(sessionInformationExpiredStrategy) // session失效策略
                .and()
                .and()
            .logout()
                .logoutUrl("/signOut")
                .logoutSuccessHandler(logoutSuccessHandler)
                .deleteCookies("JSESSIONID")
                .and()
            .authorizeRequests()
                .antMatchers(
                        SecurityConstants.DEFAULT_UNAUTHENTICATION_URL,
                        SecurityConstants.DEFAULT_LOGIN_PROCESSING_URL_MOBILE,
                        SecurityConstants.DEFAULT_VALIDATE_CODE_URL_PREFIX + "/*",
                        securityProperties.getBrowser().getLoginPage(),
                        securityProperties.getBrowser().getSignUpUrl(),
                        securityProperties.getBrowser().getSignOutUrl(),
                        securityProperties.getBrowser().getSession().getSessionInvalidUrl() + ".html",
                        securityProperties.getBrowser().getSession().getSessionInvalidUrl() + ".json",
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

