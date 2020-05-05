package com.cplh.springboot.security.core.authorize;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;

/**
 * 用来分离BrowserSecurityConfig中的这些配置，分成多个模块，而不是都写在BrowserSecurityConfig中
 * authorizeRequests().antMatchers(
 *                         SecurityConstants.DEFAULT_UNAUTHENTICATION_URL,
 *                         securityProperties.getBrowser().getLoginPage(),
 *                         "/user/regist"
 *                     )
 *                  .permitAll()
 *                 .antMatchers(HttpMethod.GET, "/user/*")
 *                 .hasRole("ADMIN")
 *                 .anyRequest()
 *                 .authenticated()
 */
public interface AuthorizeConfigProvider {

    /**
     *
     * @param config authorizeRequests() 方法的返回参数
     */
    void config(ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry config);

}
