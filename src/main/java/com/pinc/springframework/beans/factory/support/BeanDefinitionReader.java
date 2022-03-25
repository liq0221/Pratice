package com.pinc.springframework.beans.factory.support;

import com.pinc.springframework.beans.BeansException;
import com.pinc.springframework.core.io.Resource;
import com.pinc.springframework.core.io.ResourceLoader;

public interface BeanDefinitionReader {

    BeanDefinitionRegistry getRegistry();

    ResourceLoader getResourceLoader();

    void loadBeanDefinitions(Resource resource) throws BeansException;

    void loadBeanDefinitions(Resource... resource) throws BeansException;

    void loadBeanDefinitions(String location) throws BeansException;
}
