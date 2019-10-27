package com.cplh.springboot.security.core;

import com.cplh.springboot.security.core.properties.SecurityProperties;
import com.cplh.springboot.security.core.validate.image.ImageCodeGenerator;
import com.cplh.springboot.security.core.validate.sms.DefaultSmsCodeSender;
import com.cplh.springboot.security.core.validate.sms.SmsCodeSender;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableConfigurationProperties(SecurityProperties.class)
public class SecurityCoreConfig {

    /**
     * 图形验证码生成器，SmsCode的生成器通过@Component注册
     * @param securityProperties
     * @return
     */
    @Bean
    @ConditionalOnMissingBean(name = "imageCodeGenerator")
    public ImageCodeGenerator imageCodeGenerator(SecurityProperties securityProperties) {
        ImageCodeGenerator generator = new ImageCodeGenerator();
        generator.setSecurityProperties(securityProperties);
        return generator;
    }

    /**
     * 短信发送器。 如果没有使用默认的
     * @return
     */
    @Bean
    @ConditionalOnMissingBean(SmsCodeSender.class)
    public SmsCodeSender smsCodeSender() {
        return new DefaultSmsCodeSender();
    }

    @Bean
    @ConditionalOnMissingBean(PasswordEncoder.class)
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
