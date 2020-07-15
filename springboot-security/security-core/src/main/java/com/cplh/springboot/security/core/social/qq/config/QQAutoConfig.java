package com.cplh.springboot.security.core.social.qq.config;

import com.cplh.springboot.security.core.properties.QQProperties;
import com.cplh.springboot.security.core.properties.SecurityProperties;
import com.cplh.springboot.security.core.social.qq.connect.QQConnectionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Configuration;
import org.springframework.social.connect.ConnectionFactory;

/**
 * 系统里配了qq的app-id，这个类才生效
 */
@Configuration
@ConditionalOnProperty(prefix = "application.security.social.qq", name = "app-id")
public class QQAutoConfig extends SocialAutoConfigurerAdapter {

    @Autowired
    private SecurityProperties securityProperties;

    /**
     * 将QQConnectionFactory注入容器，QQ登录的核心
     */
    @Override
    protected ConnectionFactory<?> createConnectionFactory() {
        QQProperties qqConfig = securityProperties.getSocial().getQq();
        return new QQConnectionFactory(qqConfig.getProviderId(), qqConfig.getAppId(), qqConfig.getAppSecret());
    }
}
