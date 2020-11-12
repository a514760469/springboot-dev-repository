package com.cplh.gis.ticket.spi.dubbo;

import com.cplh.dubbo.bean.Wheel;

/**
 * @author zhanglifeng
 * @since 2020-09-23 15:06
 */
public interface WheelMaker {
    Wheel makeWheel();
}
