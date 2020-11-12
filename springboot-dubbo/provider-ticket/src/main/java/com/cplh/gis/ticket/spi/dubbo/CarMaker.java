package com.cplh.gis.ticket.spi.dubbo;

import com.alibaba.dubbo.common.extension.SPI;
import com.cplh.dubbo.bean.Car;

/**
 * @author zhanglifeng
 * @since 2020-09-23 15:05
 */
@SPI
public interface CarMaker {

    Car makeCar();

}
