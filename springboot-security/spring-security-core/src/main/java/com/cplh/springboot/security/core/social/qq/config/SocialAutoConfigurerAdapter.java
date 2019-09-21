package com.cplh.springboot.security.core.social.qq.config;

import org.springframework.core.env.Environment;
import org.springframework.social.UserIdSource;
import org.springframework.social.config.annotation.ConnectionFactoryConfigurer;
import org.springframework.social.config.annotation.SocialConfigurerAdapter;
import org.springframework.social.connect.ConnectionFactory;
import org.springframework.social.security.AuthenticationNameUserIdSource;

/**
 * springboot2.x 移除的类
 */
public abstract class SocialAutoConfigurerAdapter extends SocialConfigurerAdapter {

    public SocialAutoConfigurerAdapter() {
    }

    @Override
    public void addConnectionFactories(ConnectionFactoryConfigurer configurer, Environment environment) {
        configurer.addConnectionFactory(this.createConnectionFactory());
    }

    /**
     * @return 父类的方法返回null会报错
     */
    @Override
    public UserIdSource getUserIdSource() {
        return new AuthenticationNameUserIdSource();
    }

    protected abstract ConnectionFactory<?> createConnectionFactory();
}