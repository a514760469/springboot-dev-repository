package com.cplh.gis.ticket;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;

@EnableHystrix
@SpringBootApplication
public class ProviderTicketApplication {

    static {
        System.setProperty("dubbo.application.logger", "slf4j");
    }
    public static void main(String[] args) {
        SpringApplication.run(ProviderTicketApplication.class, args);
    }

}

