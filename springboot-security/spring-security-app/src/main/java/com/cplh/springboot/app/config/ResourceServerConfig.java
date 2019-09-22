package com.cplh.springboot.app.config;

import com.cplh.springboot.security.core.authentication.mobile.SmsCodeAuthenticationSecurityConfig;
import com.cplh.springboot.security.core.properties.SecurityProperties;
import com.cplh.springboot.security.core.properties.constant.SecurityConstants;
import com.cplh.springboot.security.core.validate.ValidateCodeFilter;
import com.cplh.springboot.security.core.validate.ValidateCodeSecurityConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.security.web.session.InvalidSessionStrategy;
import org.springframework.security.web.session.SessionInformationExpiredStrategy;
import org.springframework.social.security.SpringSocialConfigurer;

import javax.sql.DataSource;

@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {

    @Autowired
    private SecurityProperties securityProperties;

    @Autowired
    private SmsCodeAuthenticationSecurityConfig smsCodeAuthenticationSecurityConfig;

    @Autowired
    private ValidateCodeSecurityConfig validateCodeSecurityConfig;


    @Autowired
    private SpringSocialConfigurer springSocialConfigurer;

    @Autowired
    protected AuthenticationSuccessHandler appAuthenticationSuccessHandler;

    @Autowired
    protected AuthenticationFailureHandler appAuthenticationFailureHandler;

    @Override
    public void configure(HttpSecurity http) throws Exception {

        http.formLogin()
                .loginPage(SecurityConstants.DEFAULT_UNAUTHENTICATION_URL)      // 表单登录的登录页
                .loginProcessingUrl(SecurityConstants.DEFAULT_LOGIN_PROCESSING_URL_FORM)
                .successHandler(appAuthenticationSuccessHandler)
                .failureHandler(appAuthenticationFailureHandler);

        http.apply(validateCodeSecurityConfig)
                .and()
            .apply(smsCodeAuthenticationSecurityConfig)
                .and()
            .apply(springSocialConfigurer)
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
}
