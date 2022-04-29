package com.pinc.springframework.beans.factory.config;

import com.pinc.springframework.beans.BeansException;

public interface InstantiationAwareBeanPostProcessor extends BeanPostProcessor{

    /**
     * 在实例化之前执行该方法
     * @param beanClass
     * @param beanName
     * @return
     * @throws BeansException
     */
    Object postProcessBeforeInitialization(Class<?> beanClass, String beanName) throws BeansException;
}
