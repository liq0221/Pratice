package com.pinc.springframework.beans.factory.support;

import com.pinc.springframework.beans.BeansException;
import com.pinc.springframework.beans.factory.FactoryBean;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 注册FactoryBean的服务,放到不同的类就是希望做到不同领域处理各自的逻辑，
 * 不会导致类过大难以维护
 */
public class FactoryBeanRegistrySupport extends DefaultSingletonBeanRegistry{

    private final Map<String, Object> factoryBeanObjectCached = new ConcurrentHashMap<>();

    /**
     * 单例的情况下从内存中获取bean对象
     * @param beanName
     * @return
     */
    protected Object getCachedObjectForFactoryBean(String beanName) {
        Object object = this.factoryBeanObjectCached.get(beanName);
        return object != NULL_OBJECT ? object : null;
    }

    /**
     * 单例的情况下从内存中获取bean对象,非单例通过factoryBean创建
     * @param factoryBean
     * @param beanName
     * @return
     */
    protected Object getObjectFromFactoryBean(FactoryBean factoryBean, String beanName) {
        if (factoryBean.isSingleton()) {
            Object object = this.factoryBeanObjectCached.get(beanName);
            if (object == null) {
                object = doGetObjectFromFactoryBean(factoryBean, beanName);
                this.factoryBeanObjectCached.put(beanName, object != null ? object : NULL_OBJECT);
            }
            return object;
        } else {
            return doGetObjectFromFactoryBean(factoryBean, beanName);
        }
    }

    /**
     * 具体调用factoryBean.getObject()
     * @param factoryBean
     * @param beanName
     * @return
     */
    private Object doGetObjectFromFactoryBean(FactoryBean factoryBean, String beanName) {
        try {
            return factoryBean.getObject();
        } catch (Exception e) {
            throw new BeansException("FactoryBean threw an exception on object [" + beanName + "] creation", e);
        }
    }


}
