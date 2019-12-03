package com.gds.springboot.rabbitmq.rabbit.consumer;

import com.gds.springboot.rabbitmq.model.User;
import com.gds.springboot.rabbitmq.rabbit.message.convert.JPEGMessageConverter;
import com.gds.springboot.rabbitmq.rabbit.message.convert.TextMessageConverter;
import org.springframework.amqp.core.AcknowledgeMode;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.amqp.rabbit.listener.api.ChannelAwareMessageListener;
import org.springframework.amqp.support.converter.ContentTypeDelegatingMessageConverter;
import org.springframework.amqp.support.converter.DefaultJackson2JavaTypeMapper;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

/**
 * @author zhanglifeng
 * @date 2019/11/12/0012
 */
@Configuration
public class MQConfig {

//    @Bean
//    public ConnectionFactory connectionFactory(){
//        CachingConnectionFactory factory = new CachingConnectionFactory();
//        factory.setUri("amqp://zhihao.miao:123456@192.168.1.131:5672");
//        return factory;
//    }
//
//    @Bean
//    public RabbitAdmin rabbitAdmin(ConnectionFactory connectionFactory){
//        RabbitAdmin rabbitAdmin = new RabbitAdmin(connectionFactory);
//        return rabbitAdmin;
//    }

//    @Bean
//    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory){
//        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
//        rabbitTemplate.setMandatory(true);
//        return rabbitTemplate;
//    }

    /**
     * ContentTypeDelegatingMessageConverter是一个代理的MessageConverter。
     * ContentTypeDelegatingMessageConverter本身不做消息转换的具体动作，
     *      而是将消息转换委托给具体的MessageConverter
     * ContentTypeDelegatingMessageConverter还有一个默认的MessageConverter，
     *      也就是说当根据ContentType没有找到映射的MessageConverter的时候，就会使用默认的MessageConverter
     *
     */
//    @Bean
    public SimpleMessageListenerContainer messageListenerContainer(ConnectionFactory connectionFactory) {
        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);
        container.setQueueNames("so.order");

        MessageListenerAdapter adapter = new MessageListenerAdapter(new MessageHandler());

        // 指定Json转换器
        Jackson2JsonMessageConverter jackson2JsonMessageConverter = new Jackson2JsonMessageConverter();
        DefaultJackson2JavaTypeMapper javaTypeMapper = new DefaultJackson2JavaTypeMapper();
        Map<String, Class<?>> idClassMap = new HashMap<>();
        idClassMap.put("Order", Order.class);
        idClassMap.put("User", User.class);
        javaTypeMapper.setIdClassMapping(idClassMap);


        jackson2JsonMessageConverter.setJavaTypeMapper(javaTypeMapper);
//        adapter.setMessageConverter(jackson2JsonMessageConverter);


        ContentTypeDelegatingMessageConverter contentTypeDelegatingMessageConverter =
                new ContentTypeDelegatingMessageConverter();
        TextMessageConverter textMessageConverter = new TextMessageConverter();
        JPEGMessageConverter jpegMessageConverter = new JPEGMessageConverter();
        contentTypeDelegatingMessageConverter.addDelegate(MessageProperties.CONTENT_TYPE_JSON, jackson2JsonMessageConverter);
        contentTypeDelegatingMessageConverter.addDelegate(MessageProperties.CONTENT_TYPE_TEXT_PLAIN, textMessageConverter);
        contentTypeDelegatingMessageConverter.addDelegate("image/jpg", jpegMessageConverter);
        contentTypeDelegatingMessageConverter.addDelegate("image/jpeg", jpegMessageConverter);
        contentTypeDelegatingMessageConverter.addDelegate("image/png", jpegMessageConverter);

        adapter.setMessageConverter(contentTypeDelegatingMessageConverter);

        // 设置处理器的消费消息的默认方法
        adapter.setDefaultListenerMethod("onMessage");
        container.setMessageListener(adapter);

        return container;
    }

    @Bean
    public SimpleMessageListenerContainer consumerAckContainer(ConnectionFactory connectionFactory){
        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);
        container.setQueueNames("so.order");

        // NONE 表示自动确认， 这是如果消费的时候抛出异常消息就丢了
        // MANUAL: 手动 此时使用ChannelAwareMessageListener监听器实现ack和nack
        // AUTO: 当抛出AmqpRejectAndDontRequeueException消息会被拒绝，且requeue=false
        //          抛出ImmediateAcknowledgeAmqpException 则消息会被确认
        //          其他的异常，则消息会被拒绝，且requeue=true (死循环的风险)
        container.setAcknowledgeMode(AcknowledgeMode.AUTO);
        container.setDefaultRequeueRejected(false);// 不会重新入队列
        container.setMessageListener((ChannelAwareMessageListener) (message, channel) -> {
            System.out.println("=====接收到消息=====");
            System.out.println(new String(message.getBody()));

//            TimeUnit.SECONDS.sleep(10);
//            if (message.getMessageProperties().getHeaders().get("error") == null) {
//                channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
//                System.out.println("消息已经确认");
//            } else {
//                channel.basicNack(message.getMessageProperties().getDeliveryTag(), false, false);
//                channel.basicReject(message.getMessageProperties().getDeliveryTag(), false);
//                System.out.println("消息拒绝");
//            }

            // 相当于自己的一些消费逻辑抛错误
//            throw new ImmediateAcknowledgeAmqpException("消费消息失败！");
            throw new NullPointerException("消费消息失败! ");

        });
        return container;
    }
}
