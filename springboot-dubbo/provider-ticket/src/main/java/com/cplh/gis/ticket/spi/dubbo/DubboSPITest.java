package com.cplh.gis.ticket.spi.dubbo;

import com.alibaba.dubbo.common.extension.ExtensionLoader;
import com.cplh.gis.ticket.spi.Robot;

/**
 * @author zhanglifeng
 * @since 2020-09-07 15:16
 */
public class DubboSPITest {

    public static void main(String[] args) {
        ExtensionLoader<Robot> el = ExtensionLoader.getExtensionLoader(Robot.class);
        Robot optimusPrime = el.getExtension("optimusPrime");
        optimusPrime.sayHello();
        Robot bumblebee = el.getExtension("bumblebee");
        bumblebee.sayHello();
    }

}
