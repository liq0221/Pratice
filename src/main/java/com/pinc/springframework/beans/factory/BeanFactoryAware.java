package com.pinc.springframework.beans.factory;

import com.pinc.springframework.beans.BeansException;

/**
 * 实现此接口可以感知到所属的BeanFactory
 */
public interface BeanFactoryAware extends Aware {

    void setBeanFactory(BeanFactory beanFactory) throws BeansException;
}
