package com.gds.springboot.rabbitmq.rabbit.consumer;

/**
 * @author zhanglifeng
 * @date 2019/12/2/0002
 */
public class ConsumerAckMessageHandler {


    public void onMessage(Order order) {
        System.out.println("---------onMessage---Order-------------");
        System.out.println(order);
    }


}
