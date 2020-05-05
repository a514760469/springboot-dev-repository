package com.gds.springboot.rabbitmq.rabbit.message.convert;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.support.converter.MessageConversionException;
import org.springframework.amqp.support.converter.MessageConverter;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.UUID;

/**
 * @author zhanglifeng
 * @date 2019/11/12/0012
 */
public class JPEGMessageConverter implements MessageConverter {

    @Override
    public Message toMessage(Object object, MessageProperties messageProperties) throws MessageConversionException {
        return null;
    }

    @Override
    public Object fromMessage(Message message) throws MessageConversionException {
        System.out.println("====JPGMessageConverter====");
        byte[] body = message.getBody();
        String fileName = UUID.randomUUID().toString();
        String path = "D:/" + fileName + ".jpg";
        File file = new File(path);
        try{
            Files.copy(new ByteArrayInputStream(body), file.toPath());
        }catch (IOException e){
            e.printStackTrace();
        }
        return file;
    }
}
