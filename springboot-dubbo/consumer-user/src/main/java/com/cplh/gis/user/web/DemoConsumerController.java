package com.cplh.gis.user.web;

import com.alibaba.dubbo.config.annotation.Reference;
import com.cplh.dubbo.api.DemoService;
import com.cplh.dubbo.api.TraceIdService;
import org.springframework.util.StopWatch;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoConsumerController {

    @Reference(init = true, filter = {"myRpc"})
    private DemoService demoService;

    @Reference
    private TraceIdService traceIdService;


    @RequestMapping("/hello/{name}")
    public String sayHello(@PathVariable("name") String name) {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        String s = demoService.sayHello(name);
        stopWatch.stop();
        return s;
    }

    @RequestMapping("/trace")
    public String trace() {
        return traceIdService.traceIdTest("11");
    }
}
