package com.cplh.springboot.security.browser;

import com.cplh.springboot.security.core.authentication.AbstractChannelSecurityConfig;
import com.cplh.springboot.security.core.authentication.mobile.SmsCodeAuthenticationSecurityConfig;
import com.cplh.springboot.security.core.authorize.AuthorizeConfigManager;
import com.cplh.springboot.security.core.properties.SecurityProperties;
import com.cplh.springboot.security.core.properties.constant.SecurityConstants;
import com.cplh.springboot.security.core.validate.ValidateCodeSecurityConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
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
    private SecurityProperties securityProperties;

    @Autowired
    private DataSource dataSource;

    @Autowired
    private UserDetailsService userDetailsService;

    /**
     * 抽象配置
     */
    @Autowired
    private SmsCodeAuthenticationSecurityConfig smsCodeAuthenticationSecurityConfig;

    /**
     * 验证码相关的配置
     */
    @Autowired
    private ValidateCodeSecurityConfig validateCodeSecurityConfig;

    /**
     * 社交登录配置
     * 过滤器链上增加相应的过滤器
     * 默认拦截 /auth/
     *  /auth/{providerId}
     */
    @Autowired
    private SpringSocialConfigurer springSocialConfigurer;

    /**
     * session过期策略
     */
    @Autowired
    private SessionInformationExpiredStrategy sessionInformationExpiredStrategy;

    @Autowired
    private InvalidSessionStrategy invalidSessionStrategy;

    @Autowired
    private LogoutSuccessHandler logoutSuccessHandler;

    @Autowired
    private AuthorizeConfigManager authorizeConfigManager;

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        // 抽象配置生效，密码登录相关的配置
        applyPasswordAuthenticationConfig(http);

        // smsCodeAuthenticationSecurityConfig配置类中的所有配置加进来
        http.apply(smsCodeAuthenticationSecurityConfig)
                .and()
            .apply(validateCodeSecurityConfig)      // 验证码相关配置加进来
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
            .csrf().disable();

        authorizeConfigManager.config(http.authorizeRequests());
    }


    /**
     * rememberMe功能需要加入组件，持久化token
     */
    @Bean
    public PersistentTokenRepository persistentTokenRepository() {
        // 用户登录后将token保存到数据库里
        JdbcTokenRepositoryImpl tokenRepository = new JdbcTokenRepositoryImpl();
        tokenRepository.setDataSource(dataSource);
//        tokenRepository.setCreateTableOnStartup(true);// 启动时创建表
        return tokenRepository;
    }

}

