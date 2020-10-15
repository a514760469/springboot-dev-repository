package com.spring.cache.loopdependence;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author zhanglifeng
 * @since 2020-10-13 14:27
 */
@Service
public class ComponentB {

    private final ComponentA componentA;

    public ComponentB(ComponentA componentA) {
        this.componentA = componentA;
    }

    public void methodB() {

    }

}
