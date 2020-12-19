package com.macro.mall.common.annotation;

import java.lang.annotation.*;

/**
 * 自定义注解，有该注解的地方缓存方法会抛出异常
 * @Author Zhangnana
 * @DATE 2020/12/12 10:07
 * @Version 1.0
 */
@Documented
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface CacheException {
}
