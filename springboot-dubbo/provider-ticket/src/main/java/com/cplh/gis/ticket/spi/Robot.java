package com.cplh.gis.ticket.spi;

import com.alibaba.dubbo.common.extension.SPI;

/**
 * @author zhanglifeng
 * @since 2020-09-07 16:32
 */
@SPI
public interface Robot {

    void sayHello();
}
