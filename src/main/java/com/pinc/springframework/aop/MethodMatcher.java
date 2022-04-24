package com.pinc.springframework.aop;

import java.lang.reflect.Method;

/**
 * 方法匹配接口
 */
public interface MethodMatcher {

    /**
     * 找到表达式范围内匹配下的目标类和方法
     * @param method
     * @param targetClass
     * @return
     */
    boolean matches(Method method, Class<?> targetClass);
}
