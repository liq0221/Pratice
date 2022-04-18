package com.pinc.springframework.beans.factory;

import com.pinc.springframework.beans.BeansException;

/**
 * 实现此接口，可以感知到所属的BeanName
 */
public interface BeanNameAware extends Aware{

    void setBeanName(String name) throws BeansException;
}
