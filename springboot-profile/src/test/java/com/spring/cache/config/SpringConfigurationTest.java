package com.spring.cache.config;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class SpringConfigurationTest {

    @Test
    public void testConfig() {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(SpringConfiguration.class);

        System.out.println(applicationContext.getEnvironment());

        System.out.println(applicationContext.getApplicationName());

    }
}