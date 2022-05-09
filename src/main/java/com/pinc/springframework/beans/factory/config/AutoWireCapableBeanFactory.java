package com.pinc.springframework.beans.factory.config;

import com.pinc.springframework.beans.BeansException;
import com.pinc.springframework.beans.factory.BeanFactory;

/**
 * 自动化处理bean工厂配置的接口
 */
public interface AutoWireCapableBeanFactory extends BeanFactory {

    /**
     * 执行postProcessorBeforeInitialization方法
     * @param bean
     * @param beanName
     * @return
     * @throws BeansException
     */
    Object applyBeanPostProcessorsBeforeInitialization(Object bean, String beanName) throws BeansException;

    /**
     * 执行postProcessorAfterInitialization方法
     * @param bean
     * @param beanName
     * @return
     * @throws BeansException
     */
    Object applyBeanPostProcessorsAfterInitialization(Object bean, String beanName) throws BeansException;
}
