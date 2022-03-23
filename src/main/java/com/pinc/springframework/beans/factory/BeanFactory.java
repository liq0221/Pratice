package com.pinc.springframework.beans.factory;

/**
 * bean工厂接口
 */
public interface BeanFactory {

    Object getBean(String beanName);
}
