package com.spring.oauth2.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;

/**
 * 开启授权服务器
 * clientId：客户端标识 ID
 * secret：客户端安全码
 * scope：客户端访问范围，默认为空则拥有全部范围
 * authorizedGrantTypes：客户端使用的授权类型，默认为空
 * authorities：客户端可使用的权限
 */
@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfiguration extends AuthorizationServerConfigurerAdapter {

    /*
     * 添加客户端信息
     * 通过浏览器模拟客户端访问授权端点
     * 授权码模式   /oauth/authorize
     *
     * http://localhost:8001/oauth/authorize?client_id=client&response_type=code&redirect_uri=http://www.baidu.com
     * response_type=code : 表示使用授权码模式
     * client_id  : 让认证服务器知道是谁在请求
     * redirect_uri : 跳转网址
     * scope : 授权范围  (scope=read:表示只读)
     *
     *
     */

    /**
     * 添加客户端信息
     * @param clients
     * @throws Exception
     */
    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients
            .inMemory()                         // 使用in-memory存储客户端信息
            .withClient("client")     // client_id
            .secret("secret")                   // client_secret
            .authorizedGrantTypes("authorization_code")     // 该client允许的授权类型
            .scopes("app")                                  // 允许的授权范围
            .autoApprove(true)  // 自动批准
            .accessTokenValiditySeconds(3600);

    }

    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
        security.tokenKeyAccess("permitAll()")
                .checkTokenAccess("permitAll()")
                .allowFormAuthenticationForClients();
//        super.configure(security);
    }
}
