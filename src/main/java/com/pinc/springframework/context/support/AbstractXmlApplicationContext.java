package com.pinc.springframework.context.support;

import com.pinc.springframework.beans.factory.support.DefaultListableBeanFactory;
import com.pinc.springframework.beans.factory.xml.XmlBeanDefinitionReader;

/**
 * 通过XmlBeanDefinitionReader处理了xml的配置
 */
public abstract class AbstractXmlApplicationContext extends AbstractRefreshableApplicationContext{


    @Override
    protected void loadBeanDefinitions(DefaultListableBeanFactory beanFactory) {
        XmlBeanDefinitionReader beanDefinitionReader =
                new XmlBeanDefinitionReader(beanFactory, this);
        String[] configLocations = getConfigLocations();
        for (String configLocation : configLocations) {
            beanDefinitionReader.loadBeanDefinitions(configLocation);
        }

    }

    protected abstract String[] getConfigLocations();

}
