package com.spring.cache.loopdependence;

import junit.framework.TestCase;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author zhanglifeng
 * @since 2020-10-13 14:31
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ComponentATest extends TestCase {

    @Autowired
    private ComponentA componentA;

    @Test
    public void testMethodA() {
        System.out.println(componentA);
    }
}