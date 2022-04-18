package com.pinc.springframework.beans.factory;

import com.pinc.springframework.beans.BeansException;

/**
 * 实现此接口可以感知到所属的ClassLoader
 */
public interface BeanClassLoaderAware extends Aware{

    void setBeanClassLoader(ClassLoader classLoader) throws BeansException;
}
