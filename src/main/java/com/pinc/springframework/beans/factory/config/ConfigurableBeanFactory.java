package com.pinc.springframework.beans.factory.config;

import com.pinc.springframework.beans.factory.HierarchicalBeanFactory;

/**
 * 可获取beanPostProcessor,BeanClassLoader等的一个配置化接口
 */
public interface ConfigurableBeanFactory extends HierarchicalBeanFactory, SingletonBeanRegistry {

    String SCOPE_SINGLETON = "singleton";

    String SCOPE_PROTOTYPE = "prototype";

    void addBeanPostProcessors(BeanPostProcessor beanPostProcessor);

    /**
     * 销毁单实例
     */
    void destroySingletons();
}
