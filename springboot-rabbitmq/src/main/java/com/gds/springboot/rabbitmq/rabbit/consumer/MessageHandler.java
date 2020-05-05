package com.gds.springboot.rabbitmq.rabbit.consumer;

import com.gds.springboot.rabbitmq.model.User;

import java.io.File;
import java.util.List;
import java.util.Map;

/**
 * @author zhanglifeng
 * @date 2019/11/12/0012
 * 1、使用Jackson2JsonMessageConverter处理器，客户端发送JSON类型数据，但是没有指定消息的contentType类型，
 * 那么Jackson2JsonMessageConverter就会将消息转换成byte[]类型的消息进行消费。
 * 2、如果指定了contentType为application/json，
 * 那么消费端就会将消息转换成Map类型的消息进行消费。
 * 3、如果指定了contentType为application/json，并且生产端是List类型的JSON格式，
 * 那么消费端就会将消息转换成List类型的消息进行消费
 *
 */
public class MessageHandler {


    public void onMessage(byte[] message) {
        System.out.println("---------onMessage----byte-------------");
        System.out.println(new String(message));
    }


    public void onMessage(String message) {
        System.out.println("---------onMessage---String-------------");
        System.out.println(message);
    }

    public void onMessage(Map<String,Object> orderMaps){
        System.out.println("-------onMessage---Map<String, Object>------------");
        orderMaps.keySet().forEach(key -> System.out.println(orderMaps.get(key)));
    }

    public void onMessage(List<Order> orders){
        System.out.println("---------onMessage---List<Order>-------------");
        orders.forEach(System.out::println);
    }

    public void onMessage(Order order) {
        System.out.println("---------onMessage---Order-------------");
        System.out.println(order);
    }

    public void onMessage(User user){
        System.out.println("---------onMessage---user-------------");
        System.out.println(user.toString());
    }

    public void onMessage(File message){
        System.out.println("-------onMessage---File message------------");
        System.out.println(message.getName());
    }
}
