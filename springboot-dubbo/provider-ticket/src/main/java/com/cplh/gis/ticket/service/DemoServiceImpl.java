package com.cplh.gis.ticket.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.cplh.dubbo.api.DemoService;

@Service
public class DemoServiceImpl implements DemoService {

    @Override
    public String sayHello(String name) {
        return "hello, " + name + " (from springboot)";
    }
}
