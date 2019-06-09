package com.gds;

import com.gds.controller.HelloController;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;

@RunWith(SpringRunner.class)
@WebFluxTest(controllers = HelloController.class)
public class HelloTests {

    @Autowired
    WebTestClient webTestClient;

    @Test
    public void getHello() {
        webTestClient.get().uri("/hello").exchange().expectStatus().isOk();
    }
}
