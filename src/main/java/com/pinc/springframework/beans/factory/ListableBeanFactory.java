package com.pinc.springframework.beans.factory;

import com.pinc.springframework.beans.BeansException;

import java.util.Map;

/**
 * bean工厂的扩展接口
 * 对比于beanFactory新增了getBeansOfType(),getBeanDefinitionNames方法
 */
public interface ListableBeanFactory extends BeanFactory{

    /**
     * 通过类型返回Bean实例
     * @param type
     * @param <T>
     * @return
     * @throws BeansException
     */
    <T> Map<String, T> getBeansOfType(Class<T> type) throws BeansException;

    /**
     * 返回注册表中所有的bean名称
     * @return
     */
    String[] getBeanDefinitionNames();
}
