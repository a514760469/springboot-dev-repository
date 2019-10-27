package com.cplh.springboot.app.social;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.social.security.SpringSocialConfigurer;
import org.springframework.stereotype.Component;

@Component
public class SpringSocialConfigurerPostProcessor implements BeanPostProcessor {

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        if (StringUtils.equals(beanName, "springSocialConfigurer")) {
            SpringSocialConfigurer springSocialConfigurer = (SpringSocialConfigurer) bean;
            springSocialConfigurer.signupUrl("/social/signUp");
            return springSocialConfigurer;
        }
        return bean;
    }
}
