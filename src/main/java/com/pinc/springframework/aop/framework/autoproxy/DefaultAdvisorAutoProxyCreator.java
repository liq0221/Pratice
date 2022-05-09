package com.pinc.springframework.aop.framework.autoproxy;

import com.pinc.springframework.aop.*;
import com.pinc.springframework.aop.aspectj.AspectJExpressionPointcutAdvisor;
import com.pinc.springframework.aop.framework.ProxyFactory;
import com.pinc.springframework.aop.framework.adapter.MethodBeforeAdviceInterceptor;
import com.pinc.springframework.beans.BeansException;
import com.pinc.springframework.beans.PropertyValues;
import com.pinc.springframework.beans.factory.BeanFactory;
import com.pinc.springframework.beans.factory.BeanFactoryAware;
import com.pinc.springframework.beans.factory.config.InstantiationAwareBeanPostProcessor;
import com.pinc.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.aopalliance.aop.Advice;
import org.aopalliance.intercept.MethodInterceptor;

import java.lang.reflect.InvocationTargetException;
import java.util.Collection;

public class DefaultAdvisorAutoProxyCreator implements InstantiationAwareBeanPostProcessor, BeanFactoryAware {

    private DefaultListableBeanFactory beanFactory;

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        this.beanFactory = (DefaultListableBeanFactory) beanFactory;
    }

    @Override
    public Object postProcessBeforeInitialization(Class<?> beanClass, String beanName) throws BeansException {
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
        if (isInfrastructureClass(bean.getClass())) {
            return null;
        }
        Collection<AspectJExpressionPointcutAdvisor> advisors = beanFactory.getBeansOfType(AspectJExpressionPointcutAdvisor.class).values();
        for (AspectJExpressionPointcutAdvisor advisor : advisors) {
            ClassFilter classFilter = advisor.getPointcut().getClassFilter();
            if (!classFilter.matches(bean.getClass())) {
                continue;
            }

            AdvisedSupport advisedSupport = new AdvisedSupport();

            TargetSource targetSource = new TargetSource(bean);

            advisedSupport.setTargetSource(targetSource);
            advisedSupport.setMethodMatcher(advisor.getPointcut().getMethodMatcher());
            advisedSupport.setProxyTargetClass(false);
            advisedSupport.setMethodInterceptor((MethodInterceptor) advisor.getAdvice());
            return new ProxyFactory(advisedSupport).getProxy();
        }
        return null;
    }

    @Override
    public PropertyValues postProcessPropertyValues(PropertyValues propertyValues, Object bean, String beanName) throws BeansException {
        return propertyValues;
    }
}
