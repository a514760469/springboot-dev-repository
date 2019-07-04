package com.cplh.springboot.security.core;

import com.cplh.springboot.security.core.properties.SecurityProperties;
import com.cplh.springboot.security.core.validate.ImageCodeGenerator;
import com.cplh.springboot.security.core.validate.ValidateCodeGenerator;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(SecurityProperties.class)
public class SecurityCoreConfig {

    @Bean
    @ConditionalOnMissingBean(name = "validateCodeGenerator")
    public ValidateCodeGenerator validateCodeGenerator(SecurityProperties securityProperties) {
        ImageCodeGenerator generator = new ImageCodeGenerator();
        generator.setSecurityProperties(securityProperties);
        return generator;
    }

}
