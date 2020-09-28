package com.cplh.gis.ticket;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ProviderTicketApplication {

    static {
        System.setProperty("dubbo.application.logger", "slf4j");
    }
    public static void main(String[] args) {
        SpringApplication.run(ProviderTicketApplication.class, args);
    }

}

