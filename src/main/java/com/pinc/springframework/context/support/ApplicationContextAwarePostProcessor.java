package com.pinc.springframework.context.support;

import com.pinc.springframework.beans.BeansException;
import com.pinc.springframework.beans.factory.config.BeanPostProcessor;
import com.pinc.springframework.context.ApplicationContext;
import com.pinc.springframework.context.ApplicationContextAware;

/**
 * 由于ApplicationContext不能在创建Bean的时候获取到，
 * 所以需要把ApplicationContext写入到一个BeanPostProcessor的包装类中，
 * 然后在refresh()操作时，会加入到beanFactory中
 * 在AbstractAutowiredCapableBeanFactory.applyBeanPostProcessorBeforeInitialization()时调用。
 */
public class ApplicationContextAwarePostProcessor implements BeanPostProcessor {

    private final ApplicationContext applicationContext;

    public ApplicationContextAwarePostProcessor(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {

        if (bean instanceof ApplicationContextAware) {
            ((ApplicationContextAware) bean).setApplicationContext(applicationContext);
        }
        return bean;

    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }
}
