package com.pinc.springframework.beans;

import com.pinc.springframework.beans.factory.ConfigurableListableBeanFactory;
import com.pinc.springframework.beans.factory.config.BeanDefinition;
import com.pinc.springframework.beans.factory.config.BeanFactoryPostProcessor;

public class MyBeanFactoryPostProcessor implements BeanFactoryPostProcessor {

    @Override
    public void postProcessorBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        BeanDefinition beanDefinition = beanFactory.getBeanDefinition("userService");
        PropertyValues propertyValues = beanDefinition.getPropertyValues();
        propertyValues.addPropertyValue(new PropertyValue("company", "改为：111"));
    }
}
