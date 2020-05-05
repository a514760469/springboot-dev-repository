package com.cplh.gis.asm;

import java.lang.annotation.*;

/**
 * @author zhanglifeng
 * @date 2019/12/2/0002
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Component {
    String value() default "";
}
