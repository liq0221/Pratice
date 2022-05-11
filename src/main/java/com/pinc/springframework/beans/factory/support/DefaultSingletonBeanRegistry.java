package com.pinc.springframework.beans.factory.support;

import com.pinc.springframework.beans.BeansException;
import com.pinc.springframework.beans.factory.DisposableBean;
import com.pinc.springframework.beans.factory.ObjectFactory;
import com.pinc.springframework.beans.factory.config.SingletonBeanRegistry;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class DefaultSingletonBeanRegistry implements SingletonBeanRegistry {

    /**
     * ConcurrentHashMapbu不支持null,定义一个标志值
     */
    protected static final Object NULL_OBJECT = new Object();

    // 一级缓存 普通对象
    private Map<String, Object> singletonObjects = new ConcurrentHashMap<>();

    // 二级缓存 提前暴露的对象，没有完全实例化的对象
    private final Map<String, Object> earlySingletonObjects = new HashMap<>();

    // 三级缓存 代理对象
    private final Map<String, ObjectFactory<?>> singletonFactories = new ConcurrentHashMap<>();

    private Map<String, DisposableBean> disposableBeans = new LinkedHashMap<>();

    @Override
    public Object getSingleton(String beanName) {
        Object singletonObject = singletonObjects.get(beanName);
        if (null == singletonObject) {
            singletonObject = earlySingletonObjects.get(beanName);
            if (null == singletonObject) {
                ObjectFactory<?> singletonFactory = singletonFactories.get(beanName);
                if (null != singletonFactory) {
                    singletonObject = singletonFactory.getObject();
                    earlySingletonObjects.put(beanName, singletonObject);
                    singletonFactories.remove(beanName);
                }
            }
        }
        return singletonObject;
    }
    @Override
    public void registerSingleton(String beanName, Object singletonObject) {
        singletonObjects.put(beanName, singletonObject);
        earlySingletonObjects.remove(beanName);
        singletonFactories.remove(beanName);
    }

    protected void addSingletonFactory(String beanName, ObjectFactory<?> objectFactory) {
        if (!this.singletonObjects.containsKey(beanName)) {
            this.singletonFactories.put(beanName, objectFactory);
            this.earlySingletonObjects.remove(beanName);
        }
    }

    protected void registryDisposableBean(String beanName, DisposableBean disposableBean) {
        disposableBeans.put(beanName, disposableBean);
    }

    /**
     * DefaultSingletonBeanRegistry没有实现ConfigurableBeanFactory接口，
     * 但是DefaultSingletonBeanRegistry类中却处理了ConfigurableBeanFactory接口中的destroySingleton方法，
     * 正是因为DefaultSingletonBeanRegistry处理了destroySingleton方法，
     * 所以当AbstractBeanFactory继承了DefaultSingletonBeanRegistry之后
     * 就相当于实现了ConfigurableBeanFactory接口中destroySingleton的方法，
     * 但是DefaultSingletonBeanRegistry和ConfigurableBeanFactory是完全没有关联的
     */
    public void destroySingletons() {
        Set<String> keySet = disposableBeans.keySet();
        Object[] disposableNames = keySet.toArray();
        for (int i = disposableNames.length - 1; i >= 0; i--) {
            Object beanName = disposableNames[i];
            DisposableBean disposableBean = disposableBeans.remove(beanName);
            try {
                disposableBean.destroy();
            } catch (Exception e) {
                throw new BeansException("Destroy method on bean with name '" + beanName + "' threw an exception", e);
            }
        }
    }
}
