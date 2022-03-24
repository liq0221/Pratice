package com.pinc.springframework.beans;

import com.pinc.springframework.beans.factory.config.BeanDefinition;
import com.pinc.springframework.beans.factory.config.BeanReference;
import com.pinc.springframework.beans.factory.support.DefaultListableBeanFactory;

public class Test {

    @org.junit.jupiter.api.Test
    public void test_instantiation() {
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();

        BeanDefinition beanDefinition = new BeanDefinition(UserService.class);
        beanFactory.registryBeanDefinition("userService", beanDefinition);

        UserService userService = (UserService) beanFactory.getBean("userService");
        userService.queryUser();

    }

    @org.junit.jupiter.api.Test
    public void test_property() {

        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();

        // 注册userDao
        beanFactory.registryBeanDefinition("userDao", new BeanDefinition(UserDao.class));

        // 设置属性
        PropertyValues propertyValues = new PropertyValues();
        propertyValues.addPropertyValue(new PropertyValue("id", "10001"));
        propertyValues.addPropertyValue(new PropertyValue("userDao", new BeanReference("userDao")));

        // 注册userService
        beanFactory.registryBeanDefinition("userService1", new BeanDefinition(UserService1.class, propertyValues));

        UserService1 userService = (UserService1) beanFactory.getBean("userService1");
        userService.queryUser();

    }
}
