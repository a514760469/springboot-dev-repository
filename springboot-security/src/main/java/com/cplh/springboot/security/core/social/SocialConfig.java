package com.cplh.springboot.security.core.social;

import com.cplh.springboot.security.core.properties.SecurityProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.encrypt.Encryptors;
import org.springframework.social.config.annotation.EnableSocial;
import org.springframework.social.config.annotation.SocialConfigurerAdapter;
import org.springframework.social.connect.ConnectionFactoryLocator;
import org.springframework.social.connect.UsersConnectionRepository;
import org.springframework.social.connect.jdbc.JdbcUsersConnectionRepository;
import org.springframework.social.connect.web.ProviderSignInUtils;
import org.springframework.social.security.SpringSocialConfigurer;

import javax.sql.DataSource;

@Configuration
@EnableSocial
public class SocialConfig extends SocialConfigurerAdapter {

    @Autowired
    DataSource dataSource;

    @Autowired
    SecurityProperties securityProperties;

    @Override
    public UsersConnectionRepository getUsersConnectionRepository(ConnectionFactoryLocator connectionFactoryLocator) {
        JdbcUsersConnectionRepository repository = new JdbcUsersConnectionRepository(dataSource, connectionFactoryLocator, Encryptors.noOpText());
        repository.setTablePrefix("sp_social_");// 表的前缀
        return repository;
    }

    /**
     * 把自己实现的SpringSocialConfigurer放入容器中
     * @return
     */
    @Bean
    SpringSocialConfigurer springSocialConfigurer() {
        String filterProcessesUrl = securityProperties.getSocial().getFilterProcessesUrl();
        SecuritySpringSocialConfigurer socialConfigurer = new SecuritySpringSocialConfigurer(filterProcessesUrl);
        socialConfigurer.signupUrl(securityProperties.getBrowser().getSignUpUrl());
        return socialConfigurer;
    }


    /**
     * spring social 提供的工具类
     * 作用1 获取social登录成功后的用户信息
     *     2 生成userId后交给Spring
     * @param connectionFactoryLocator
     * @return
     */
    @Bean
    ProviderSignInUtils providerSignInUtils(ConnectionFactoryLocator connectionFactoryLocator) {
        return new ProviderSignInUtils(connectionFactoryLocator, getUsersConnectionRepository(connectionFactoryLocator));
    }
}
