package com.gds.springboot.rabbitmq.rabbit.message.convert;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.support.converter.MessageConversionException;
import org.springframework.amqp.support.converter.MessageConverter;

/**
 * @author zhanglifeng
 * @date 2019/11/12/0012
 */
public class TextMessageConverter implements MessageConverter {


    @Override
    public Message toMessage(Object object, MessageProperties messageProperties) throws MessageConversionException {
        System.out.println("=======toMessage=========");
        return new Message(object.toString().getBytes(), messageProperties);
    }

    @Override
    public Object fromMessage(Message message) throws MessageConversionException {
        System.out.println("=======fromMessage=========");
        return new String(message.getBody());
    }
}
