package com.pinc.springframework.context.support;

import com.pinc.springframework.beans.factory.ConfigurableListableBeanFactory;
import com.pinc.springframework.beans.factory.support.DefaultListableBeanFactory;

/**
 * 在refreshBeanFactory()中主要是获取了DefaultListableBeanFactory的实例化以及对资源配置的加载操作
 * loadBeanDefinitions()主要是为了加载spring.xml中bean的定义和注册，也实现了beanPostProcessor
 * 和beanFactoryPostProcessor对bean的配置信息
 * loadBeanDefinitions()是一个抽象方法 留给子类实现
 */
public abstract class AbstractRefreshableApplicationContext extends AbstractApplicationContext{

    private DefaultListableBeanFactory beanFactory;

    @Override
    protected ConfigurableListableBeanFactory getBeanFactory() {
        return beanFactory;
    }

    @Override
    protected void refreshBeanFactory() {
        beanFactory = createBeanFactory();
        loadBeanDefinitions(beanFactory);
        this.beanFactory = beanFactory;
    }

    protected abstract void loadBeanDefinitions(DefaultListableBeanFactory beanFactory);

    private DefaultListableBeanFactory createBeanFactory() {
        return new DefaultListableBeanFactory();
    }
}
