package com.pinc.springframework.aop;

import com.pinc.springframework.utils.ClassUtils;

/**
 * 被代理的目标类
 */
public class TargetSource {

    private final Object target;


    public TargetSource(Object target) {
        this.target = target;
    }

    public Class<?>[] getTargetClass() {
        Class<?> aClass = this.target.getClass();
        // 如果是cglib代理创建的对象，不能直接获取到接口
        aClass = ClassUtils.isCglibProxyClass(aClass) ? aClass.getSuperclass() : aClass;
        return aClass.getInterfaces();
    }

    public Object getTarget() {
        return this.target;
    }
}
