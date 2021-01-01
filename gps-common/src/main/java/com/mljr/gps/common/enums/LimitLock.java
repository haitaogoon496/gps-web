package com.mljr.gps.common.enums;

import java.lang.annotation.*;

/**
 * @description:
 * @Date : 2019/1/28$ 10:12$
 * @Author : liht
 */
@Target(value = {ElementType.TYPE, ElementType.METHOD, ElementType.PACKAGE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface LimitLock {


    String key() default "";

    String condition() default "";
}
