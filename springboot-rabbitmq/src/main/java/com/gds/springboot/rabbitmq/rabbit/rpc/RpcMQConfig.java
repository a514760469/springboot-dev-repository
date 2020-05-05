package com.gds.springboot.rabbitmq.rabbit.rpc;

import org.springframework.amqp.core.AcknowledgeMode;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author zhanglifeng
 * @date 2019/12/3/0003
 */
@Configuration
public class RpcMQConfig {

    /**
     * 监听了sms队列
     * 配置了适配器，适配器中去调用服务
     * 适配器返回的值就是服务端返回给客户端的RPC调用的结果 这里返回是boolean类型
     * @param connectionFactory
     * @return
     */
    @Bean
    public SimpleMessageListenerContainer rpcMessageListenerContainer(ConnectionFactory connectionFactory){

        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);
        container.setQueueNames("sms");
        container.setAcknowledgeMode(AcknowledgeMode.NONE);
        // 使用适配器的方式
        container.setMessageListener(new MessageListenerAdapter(new SendSMSHandler(), new Jackson2JsonMessageConverter()));
        return container;
    }



    @Bean
    public Queue smsQueue() {
        return new Queue("sms", true, false, false);
    }
}
