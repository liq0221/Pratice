package com.pinc.springframework.aop;

/**
 * 类匹配接口
 */
public interface ClassFilter {

    /**
     * 切点找到给定的接口和目标类
     * @param clazz
     * @return
     */
    boolean matches(Class<?> clazz);
}
