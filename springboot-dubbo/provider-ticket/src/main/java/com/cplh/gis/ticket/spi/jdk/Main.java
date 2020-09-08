package com.cplh.gis.ticket.spi.jdk;

import java.util.ServiceLoader;

/**
 * 扫描 META-INF/services/com.cplh.gis.ticket.spi.jdk.IPerson文件下配的实现类
 * @author zhanglifeng
 * @since 2020-09-07 14:28
 */
public class Main {


    public static void main(String[] args) {
        ServiceLoader<IPerson> sl = ServiceLoader.load(IPerson.class);
        for (IPerson next : sl) {
            next.say();
        }
    }
}
