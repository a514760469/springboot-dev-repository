package com.cplh.gis.ticket.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.cplh.dubbo.api.DemoService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@Service(timeout = 60000)
public class DemoServiceImpl implements DemoService {

    @HystrixCommand(fallbackMethod = "sayHelloFallback")
    @Override
    public String sayHello(String name) {
        if (Math.random() > 0.5) {
            throw new RuntimeException();
        }
        return "hello, " + name + " (from springboot)";
    }

    private String sayHelloFallback(String name) {
        return "hello, sayHelloFallback (from springboot)";
    }
}
