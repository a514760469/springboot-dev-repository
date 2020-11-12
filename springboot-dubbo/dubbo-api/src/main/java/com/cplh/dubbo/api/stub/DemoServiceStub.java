package com.cplh.dubbo.api.stub;

import com.cplh.dubbo.api.DemoService;

/**
 * @author zhanglifeng
 * @since 2020-09-30 14:57
 */
public class DemoServiceStub implements DemoService {

    private final DemoService demoService;

    public DemoServiceStub(DemoService demoService) {
        this.demoService = demoService;
    }

    @Override
    public String sayHello(String name) {
        if (name == null || "xxx".equals(name)) {
            return "error";
        }
        return demoService.sayHello(name);
    }
}
