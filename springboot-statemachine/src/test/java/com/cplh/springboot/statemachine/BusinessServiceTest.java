package com.cplh.springboot.statemachine;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author zhanglifeng
 * @since 2020-06-03 14:40
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class BusinessServiceTest {

    @Autowired
    BusinessService businessService;

    @Test
    public void stateChange() {
        businessService.stateChange();
    }
}