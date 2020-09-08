package com.cplh.gis.ticket.spi.dubbo;

import com.alibaba.dubbo.common.extension.ExtensionLoader;
import com.cplh.gis.ticket.spi.jdk.IPerson;
import org.junit.Test;

/**
 * @author zhanglifeng
 * @since 2020-09-07 17:04
 */
public class DubboSPITestTest {

    @Test
    public void testDubboSPI() {
        ExtensionLoader<IPerson> el = ExtensionLoader.getExtensionLoader(IPerson.class);
        IPerson nuanNan = el.getExtension("nuanNan");
        nuanNan.say();
    }

}