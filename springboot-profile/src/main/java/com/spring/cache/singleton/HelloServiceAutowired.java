package com.spring.cache.singleton;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author zhanglifeng
 * @since 2020-09-01 16:55
 */
@Service
public class HelloServiceAutowired {

    @Autowired
    HelloService helloService;

    public void hello() {
        System.out.println(helloService);
    }
}
