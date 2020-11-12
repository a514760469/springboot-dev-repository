package com.cplh.gis.ticket.spi.dubbo;

import com.cplh.dubbo.bean.Car;

/**
 * @author zhanglifeng
 * @since 2020-09-23 17:38
 */
public class NormalCarMaker implements CarMaker {

    @Override
    public Car makeCar() {
        System.out.println("NormalCarMaker.makeCar");
        return new Car();
    }
}
