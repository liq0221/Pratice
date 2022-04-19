package com.pinc.springframework.beans.factory.support;

import com.pinc.springframework.beans.BeansException;
import com.pinc.springframework.beans.factory.DisposableBean;
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

    private Map<String, Object> singletonBeans = new ConcurrentHashMap<>();

    private Map<String, DisposableBean> disposableBeans = new LinkedHashMap<>();

    @Override
    public Object getSingleton(String beanName) {
        return singletonBeans.get(beanName);
    }

    // protected修饰的属性/方法，只能被类内和子类调用
    protected void addSingleton(String beanName, Object singletonObject) {
        singletonBeans.put(beanName, singletonObject);
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
