package com.cplh;

import org.apache.commons.lang3.StringUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

//@RunWith(SpringRunner.class)
//@SpringBootTest
public class SpringbootWeb01ApplicationTests {

    @Test
    public void contextLoads() {
        String str = "收到,121,sa,我玩的,";
        String res = StringUtils.substringBeforeLast(str, ",");
        System.out.println(res);
    }

}
