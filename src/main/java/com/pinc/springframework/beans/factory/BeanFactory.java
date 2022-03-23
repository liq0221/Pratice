package com.pinc.springframework.beans.factory;

import com.pinc.springframework.beans.BeansException;

/**
 * bean工厂接口
 */
public interface BeanFactory {

    Object getBean(String beanName) throws BeansException;

    Object getBean(String beanName, Object... args) throws BeansException;
}
