package com.pinc.springframework.beans.factory.support;

import com.pinc.springframework.beans.BeansException;
import com.pinc.springframework.beans.factory.config.BeanDefinition;

public interface BeanDefinitionRegistry {

    /**
     * 注册beanDefinition
     * @param beanName
     * @param beanDefinition
     */
    void registryBeanDefinition(String beanName, BeanDefinition beanDefinition);

    /**
     * 通过beanName获取beanDefinition
     * @param beanName
     * @return
     * @throws BeansException
     */
    BeanDefinition getBeanDefinition(String beanName) throws BeansException;

    /**
     * 判断是否包含指定名称的BeanDefinition
     * @param beanName
     * @return
     */
    boolean containsBeanDefinition(String beanName);

    /**
     * 返回所有注册表中的beanName
     * @return
     */
    String[] getBeanDefinitionNames();

}
