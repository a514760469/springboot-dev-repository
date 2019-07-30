package com.cplh.gis.amqp.service;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import com.cplh.gis.amqp.bean.Book;

@Service
public class BookService {

	@RabbitListener(queues = "spring.news")
	public void receive(Book book) {
		System.out.println("收到消息：" + book);
	}
	
	@RabbitListener(queues = "spring")
	public void receiveMessage(Message message) {
		System.out.println(message.getMessageProperties());
		System.out.println(message.getBody());
		
	}
}

