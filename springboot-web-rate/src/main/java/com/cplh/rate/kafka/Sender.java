package com.cplh.rate.kafka;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.UUID;

/**
 * @author zhanglifeng
 * @since 2021-01-07
 */
@Slf4j
@Component
public class Sender {

    @Autowired
    KafkaTemplate<String, String> kafkaTemplate;

    public void send() {
        KMessage message = new KMessage();
        message.setId(System.currentTimeMillis());
        message.setMessage(UUID.randomUUID().toString());
        message.setDate(new Date());
        log.info("发送message = {}", JSON.toJSONString(message));
        kafkaTemplate.send("test", JSON.toJSONString(message));
    }

}
