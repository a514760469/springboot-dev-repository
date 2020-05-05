package com.cplh.gis.amqp.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
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

	@Bean
	public DirectExchange directExchange() {
		return new DirectExchange("exchange.direct");
	}

	@Bean
	public Queue queueMessage() {
		return new Queue("news");
	}

	@Bean
	public Binding binding() {
		return BindingBuilder.bind(queueMessage()).to(directExchange()).with("spring.news");
	}
}
