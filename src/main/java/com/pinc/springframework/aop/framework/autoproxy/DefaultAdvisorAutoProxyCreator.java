package com.pinc.springframework.aop.framework.autoproxy;

import com.pinc.springframework.aop.Advisor;
import com.pinc.springframework.aop.Pointcut;
import com.pinc.springframework.beans.BeansException;
import com.pinc.springframework.beans.factory.BeanFactory;
import com.pinc.springframework.beans.factory.BeanFactoryAware;
import com.pinc.springframework.beans.factory.config.InstantiationAwareBeanPostProcessor;
import com.pinc.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.aopalliance.aop.Advice;

public class DefaultAdvisorAutoProxyCreator implements InstantiationAwareBeanPostProcessor, BeanFactoryAware {

    private DefaultListableBeanFactory beanFactory;

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        this.beanFactory = (DefaultListableBeanFactory) beanFactory;
    }

    @Override
    public Object postProcessBeforeInitialization(Class<?> beanClass, String beanName) throws BeansException {
        if (isInfrastructureClass(beanClass)) {
            return null;
        }

        return null;
    }

    private boolean isInfrastructureClass(Class<?> beanClass) {
        return Advice.class.isAssignableFrom(beanClass) || Pointcut.class.isAssignableFrom(beanClass)
                || Advisor.class.isAssignableFrom(beanClass);
    }

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }
}
