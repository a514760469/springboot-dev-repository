package com.cplh.gis.ticket.spi.dubbo;

import com.alibaba.dubbo.common.extension.ExtensionLoader;

/**
 * @author zhanglifeng
 * @since 2020-09-07 15:16
 */
public class DubboSPITest {

    static {
        System.setProperty("dubbo.application.logger", "slf4j");
    }

    public static void main(String[] args) {
        ExtensionLoader<Robot> el = ExtensionLoader.getExtensionLoader(Robot.class);
        Robot optimusPrime = el.getExtension("optimusPrime");
        optimusPrime.sayHello();
        Robot bumblebee = el.getExtension("bumblebee");
        bumblebee.sayHello();

//        ExtensionLoader<CarMaker> carEl = ExtensionLoader.getExtensionLoader(CarMaker.class);
//        List<CarMaker> cache = carEl.getActivateExtension(URL.valueOf("localhost/w?cache=1"), "cache");
//        CarMaker cache = carEl.getExtension("cache");
//        System.out.println(cache);
    }

}
