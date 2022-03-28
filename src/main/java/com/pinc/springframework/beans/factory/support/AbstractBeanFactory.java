package com.pinc.springframework.beans.factory.support;

import com.pinc.springframework.beans.BeansException;
import com.pinc.springframework.beans.factory.BeanFactory;
import com.pinc.springframework.beans.factory.config.BeanDefinition;
import com.pinc.springframework.beans.factory.config.BeanPostProcessor;
import com.pinc.springframework.beans.factory.config.ConfigurableBeanFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * 1.AbstractBeanFactory继承了DefaultSingletonBeanRegistry类，拥有了获取单例bean对象的方法
 * 2.实现了的BeanFactory接口，从而实现getBean方法，在获取单例bean对象时如果获取不到，会去调用定义的getBeanDefinition抽象方法
 *   获取实例化好的bean(这里AbstractBeanFactory不会自己去实现getBeanDefinition抽象方法，留给子类实现)
 */
public abstract class AbstractBeanFactory extends DefaultSingletonBeanRegistry implements ConfigurableBeanFactory {

    private List<BeanPostProcessor> beanPostProcessors = new ArrayList<>();


    @Override
    public Object getBean(String beanName) throws BeansException{
        return doGetBean(beanName, null);
    }

    @Override
    public Object getBean(String beanName, Object... args) throws BeansException {
        return doGetBean(beanName, args);
    }

    @Override
    public <T> T getBean(String name, Class<T> requiredType) throws BeansException {
        return (T) getBean(name);
    }

    protected <T> T doGetBean(String beanName, Object[] args) {
        Object bean = getSingleton(beanName);
        if (bean != null) {
            return (T) bean;
        }
        BeanDefinition beanDefinition = getBeanDefinition(beanName);
        return (T) createBean(beanName,  beanDefinition, args);
    }

    /**
     * 抽象的getBeanDefinition方法，留给子类实现
     * @param beanName
     * @return
     * @throws BeansException
     */
    protected abstract BeanDefinition getBeanDefinition(String beanName) throws BeansException;

    /**
     * 抽象的createBean方法，留给子类实现
     * @param beanName
     * @param beanDefinition
     * @return
     * @throws BeansException
     */
    protected abstract Object createBean(String beanName, BeanDefinition beanDefinition, Object[] args) throws BeansException;

    @Override
    public void addBeanPostProcessors(BeanPostProcessor beanPostProcessor) {
        this.beanPostProcessors.remove(beanPostProcessor);
        this.beanPostProcessors.add(beanPostProcessor);
    }

    public List<BeanPostProcessor> getBeanPostProcessors() {
        return this.beanPostProcessors;
    }
}
