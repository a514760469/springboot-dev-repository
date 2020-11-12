package com.cplh.gis.user;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ConsumerUserApplication {

    static {
        System.setProperty("dubbo.application.logger", "slf4j");
    }

    public static void main(String[] args) {
        SpringApplication.run(ConsumerUserApplication.class, args);
    }

}

