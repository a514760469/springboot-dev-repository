package com.gds.springboot.rabbitmq.rabbit;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class RabbitConfig {

    @Bean
    public Queue helloQueue() {
        return new Queue("hello");
    }

    @Bean
    public Queue neoQueue() {
        return new Queue("neo");
    }

    @Bean
    public Queue objectQueue() {
        return new Queue("object");
    }


    // 死信队列实现延迟任务

//    // 死信队列
//    private String deadLetterExchange = "dlx.exchange";
//
//    private String deadLetterQueue = "dlx.queue";
//
//    private String deadLetterRoutingKey = "dlx.routingKey";
//    // 转发队列
//    private String ttlQueue = "ttl.queue";
//
//    private String ttlExchange = "ttl.exchange";
//
//    private String ttlRoutingKey = "ttl.routingKey";
//
//    /**
//     * ttl超时时间
//     */
//    private String ttlExpiration = "1800000";

//    /**
//     * 死信交换机
//     *
//     * @return
//     */
//    @Bean
//    public DirectExchange deadLetterExchange() {
//        return new DirectExchange(rabbitMQProperties.getDeadLetterExchange());
//    }
//
//    /**
//     * 死信队列
//     *
//     * @return
//     */
//    @Bean
//    public Queue deadLetterQueue() {
//        return new Queue(rabbitMQProperties.getDeadLetterQueue());
//    }
//
//
//    @Bean
//    public Binding deadLetterBinding() {
//        return BindingBuilder.bind(deadLetterQueue())
//                .to(deadLetterExchange())
//                .with(rabbitMQProperties.getDeadLetterRoutingKey());
//    }
//
//
//    /**
//     * 过期队列
//     *
//     * @return
//     */
//    @Bean
//    public Queue ttlQueue() {
//        Map<String, Object> args = new HashMap<>();
//        // 以下参数配置具体查看官方文档或者到rabbit管理后台添加queue中查看
//        // 配置死信队列的交换机
//        args.put("x-dead-letter-exchange", rabbitMQProperties.getDeadLetterExchange());
//        // 配置死信队列的路由routingKey
//        args.put("x-dead-letter-routing-key", rabbitMQProperties.getDeadLetterRoutingKey());
//        // 设置过期时间
////        args.put("x-message-ttl", 10000);
//        return new Queue(rabbitMQProperties.getTtlQueue(), true, false, false, args);
//    }
//
//    @Bean
//    public Binding ttlBinding() {
//        return BindingBuilder.bind(ttlQueue())
//                .to(deadLetterExchange())
//                .with(rabbitMQProperties.getTtlRoutingKey());
//    }
}
