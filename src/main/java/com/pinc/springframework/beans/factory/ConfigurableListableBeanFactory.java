package com.pinc.springframework.beans.factory;

import com.pinc.springframework.beans.BeansException;
import com.pinc.springframework.beans.factory.config.AutoWireCapableBeanFactory;
import com.pinc.springframework.beans.factory.config.BeanDefinition;
import com.pinc.springframework.beans.factory.config.ConfigurableBeanFactory;

/**
 * 提供分析和修改bean以及预先实例化的操作接口
 */
public interface ConfigurableListableBeanFactory extends ListableBeanFactory, AutoWireCapableBeanFactory, ConfigurableBeanFactory {

    BeanDefinition getBeanDefinition(String beanName) throws BeansException;
}
