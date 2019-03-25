package com.cplh.gis.amqp;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.Binding.DestinationType;
import org.springframework.amqp.rabbit.core.RabbitMessagingTemplate;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.cplh.gis.amqp.bean.Book;

/**
 * 自动配置
 * CachingConnectionFactory 
 * RabbitProperties 封装rabbitMQ配置
 * RabbitTemplate 
 * AmqpAdmin rabbitMQ系统管理组件
 * 		创建和删除Queue、Exchange、Binding
 * 
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringBootAmqpTests {

	@Autowired
	RabbitTemplate rabbitTemplate;
	
	@Autowired
	RabbitMessagingTemplate rabbitMessagingTemplate;
	
	@Autowired
	AmqpAdmin amqpAdmin;
	
	@Test
	public void createExchange() {
//		amqpAdmin.declareExchange(new DirectExchange("amqpAdmin.exchange"));
//		System.out.println("建好了");
//		amqpAdmin.declareQueue(new Queue("amqpAdmin.queue", true));
		amqpAdmin.declareBinding(new Binding("amqpAdmin.queue", DestinationType.QUEUE, "amqpAdmin.exchange", "amqpAdmin.queue", null));
		
	}
	/**
	 * 1 单播（ 点对点）
	 */
	@Test
	public void contextLoads() {
		// message自己构造
//		rabbitTemplate.send(exchange, routingKey, message);
		// 自动序列化 Object
		Map<String, Object> map = new HashMap<>();
		map.put("msg", "消息消息消息消息消息消息");
		map.put("data", Arrays.asList("helloworld", 123, true));
		Book book = new Book("参考书", "参考");
		rabbitTemplate.convertAndSend("exchange.direct", "spring.news", new Book("西游记", "吴承恩", book));
		
	}
	/**
	 * 2 广播
	 */
	@Test
	public void sendFanout() {
		rabbitTemplate.convertAndSend("exchange.fanout", "", new Book("三国演义", "小罗"));
	}
	/**
	 * 
	 */
	@Test
	public void sendTopic() {
		rabbitTemplate.convertAndSend("exchange.topic", "sp.news", "咩哈哈");
	}
	
	
	/**
	 * 接收
	 */
	@Test
	public void receive() {
		Object object = rabbitTemplate.receiveAndConvert("spring.news");
		System.out.println(object.getClass());
		System.out.println(object);
	}
	
}
