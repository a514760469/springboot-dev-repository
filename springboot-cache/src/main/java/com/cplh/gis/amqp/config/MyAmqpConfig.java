package com.cplh.gis.amqp.config;

import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableRabbit 	// 开启基于注解的rabbitMQ
public class MyAmqpConfig {

	@Bean
	public MessageConverter messageConverter () {
		return new Jackson2JsonMessageConverter();
	}
	
}
