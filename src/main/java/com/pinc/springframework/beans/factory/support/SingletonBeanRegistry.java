package com.pinc.springframework.beans.factory.support;

/**
 * 单例注册接口
 */
public interface SingletonBeanRegistry {

    /**
     * 获取单例bean对象
     * @param beanName
     * @return
     */
    Object getSingleton(String beanName);
}