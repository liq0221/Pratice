package com.pinc.springframework.beans.factory;

import com.pinc.springframework.beans.BeansException;

import java.beans.Beans;

/**
 * bean工厂接口
 */
public interface BeanFactory {

    Object getBean(String beanName) throws BeansException;

    Object getBean(String beanName, Object... args) throws BeansException;

    <T> T getBean(String name, Class<T> requiredType) throws BeansException;

    <T> T getBean(Class<T> requiredType);
}
