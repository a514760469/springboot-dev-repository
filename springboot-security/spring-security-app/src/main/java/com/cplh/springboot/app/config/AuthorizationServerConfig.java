package com.cplh.springboot.app.config;

import com.cplh.springboot.security.core.properties.OAuth2ClientProperties;
import com.cplh.springboot.security.core.properties.SecurityProperties;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.builders.InMemoryClientDetailsServiceBuilder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;

@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private SecurityProperties securityProperties;

    @Autowired
    private TokenStore tokenStore;

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints.authenticationManager(authenticationManager)
                .userDetailsService(userDetailsService)
                .tokenStore(tokenStore);
    }


    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        InMemoryClientDetailsServiceBuilder builder = clients.inMemory();
        if (CollectionUtils.isNotEmpty(securityProperties.getOauth2().getClient())) {
            for (OAuth2ClientProperties clientProperties : securityProperties.getOauth2().getClient()) {
                builder.withClient(clientProperties.getClientId())
                        .secret(passwordEncoder.encode(clientProperties.getSecret()))
                        .accessTokenValiditySeconds(7200)   // 发出去的token有效期 单位：秒
                        .authorizedGrantTypes("refresh_token", "password")
                        .scopes("all", "read", "write");
            }
        }
    }

    /**
     * spring security在最新版本升级后,默认把之前的明文密码方式给去掉了
     */
    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {

    }
}
