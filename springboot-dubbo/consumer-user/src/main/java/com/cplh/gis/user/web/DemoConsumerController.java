package com.cplh.gis.user.web;

import com.alibaba.dubbo.config.annotation.Reference;
import com.cplh.dubbo.api.DemoService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoConsumerController {

    @Reference
    private DemoService demoService;

    @RequestMapping("/hello/{name}")
    public String sayHello(@PathVariable("name") String name) {
        return demoService.sayHello(name);
    }

}
