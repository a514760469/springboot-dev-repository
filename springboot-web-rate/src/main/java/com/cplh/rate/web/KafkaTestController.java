package com.cplh.rate.web;

import com.cplh.rate.kafka.Sender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zhanglifeng
 * @since 2021-01-07
 */
@RestController
@RequestMapping("/kafka")
public class KafkaTestController {

    @Autowired
    private Sender sender;

    @GetMapping("/test")
    public String testSend() {
        sender.send();
        sender.send();
        sender.send();
        return "success";
    }

}
