package com.cplh.gis.ticket.spi.jdk;

import com.alibaba.dubbo.common.extension.SPI;

/**
 * @author zhanglifeng
 * @since 2020-09-07 14:24
 */
@SPI
public interface IPerson {

    void say();
}
