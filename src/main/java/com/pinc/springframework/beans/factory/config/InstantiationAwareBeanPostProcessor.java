package com.pinc.springframework.beans.factory.config;

import com.pinc.springframework.beans.BeansException;
import com.pinc.springframework.beans.PropertyValues;

public interface InstantiationAwareBeanPostProcessor extends BeanPostProcessor{

    /**
     * 在实例化之前执行该方法
     * @param beanClass
     * @param beanName
     * @return
     * @throws BeansException
     */
    Object postProcessBeforeInitialization(Class<?> beanClass, String beanName) throws BeansException;

    /**
     * 进行属性的提取和设置
     * @param propertyValues
     * @param bean
     * @param beanName
     * @return
     * @throws BeansException
     */
    PropertyValues postProcessPropertyValues(PropertyValues propertyValues, Object bean, String beanName) throws BeansException;
}
