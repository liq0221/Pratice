package com.pinc.springframework.beans.factory.support;

import com.pinc.springframework.beans.factory.config.BeanDefinition;

public interface BeanDefinitionRegistry {

    /**
     * 注册beanDefinition
     * @param beanName
     * @param beanDefinition
     */
    void registryBeanDefinition(String beanName, BeanDefinition beanDefinition);
}
