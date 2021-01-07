package com.cplh.rate.kafka;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.Optional;

/**
 * @author zhanglifeng
 * @since 2021-01-07
 */
@Slf4j
@Component
public class Receiver {

    @KafkaListener(topics = "test", groupId = "test")
    public void receiver(ConsumerRecord<?, ?> record, @Headers Map<String, Object> headers) {

        System.out.println(JSON.toJSONString(headers));

        Optional<?> message = Optional.ofNullable(record.value());
        if (message.isPresent()) {
            log.info("receiver record = " + record);
            log.info("receiver message = " + message.get());
        }

    }

}
