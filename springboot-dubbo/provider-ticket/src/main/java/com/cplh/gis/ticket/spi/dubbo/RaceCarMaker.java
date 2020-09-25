package com.cplh.gis.ticket.spi.dubbo;

import com.alibaba.dubbo.common.extension.Activate;
import com.cplh.dubbo.bean.Car;

/**
 * @author zhanglifeng
 * @since 2020-09-23 15:17
 */
@Activate   //  无条件自动激活
public class RaceCarMaker implements CarMaker {

    @Override
    public Car makeCar() {
        System.out.println("RaceCarMaker.makeCar");
        return new Car();
    }
}
