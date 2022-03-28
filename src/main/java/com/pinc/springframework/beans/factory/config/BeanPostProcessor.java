package com.pinc.springframework.beans.factory.config;

import com.pinc.springframework.beans.BeansException;

/**
 * BeanPostProcessor，也是 Spring 提供的扩展机制，
 * 不过 BeanPostProcessor 是在 Bean 对象实例化之后修改 Bean 对象，
 * 也可以替换 Bean 对象。这部分与后面要实现的 AOP 有着密切的关系
 */
public interface BeanPostProcessor {

    /**
     * 在实例化bean之前执行此方法
     * @param bean
     * @param beanName
     * @return
     * @throws BeansException
     */
    Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException;

    /**
     * 在实例化bean之后执行此方法
     * @param bean
     * @param beanName
     * @return
     * @throws BeansException
     */
    Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException;
}
