package com.pinc.springframework.beans.factory.config;

import com.pinc.springframework.beans.BeansException;
import com.pinc.springframework.beans.factory.ConfigurableListableBeanFactory;

/**
 * BeanFactoryPostProcessor，是由 Spring 框架组建提供的容器扩展机制，
 * 允许在 Bean 对象注册后但未实例化之前，对 Bean 的定义信息 BeanDefinition 执行修改操作
 */
public interface BeanFactoryPostProcessor {

    /**
     * 在所有的beanDefinition加载完成后，在bean实例化之前，提供修改beanDefinition属性的机制
     * @param beanFactory
     * @throws BeansException
     */
    void postProcessorBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException;
}
