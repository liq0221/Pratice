package com.pinc.springframework.beans.factory.support;

import cn.hutool.core.util.StrUtil;
import com.pinc.springframework.beans.BeansException;
import com.pinc.springframework.beans.factory.DisposableBean;

import java.lang.reflect.Method;

public class DisposableBeanAdapter implements DisposableBean {

    private final Object bean;

    private final String beanName;

    private String destroyMethodName;

    public DisposableBeanAdapter(Object bean, String beanName, String destroyMethodName) {
        this.bean = bean;
        this.beanName = beanName;
        this.destroyMethodName = destroyMethodName;
    }

    @Override
    public void destroy() throws Exception {

        if (bean instanceof DisposableBean) {
            ((DisposableBean) bean).destroy();
        }
        if (StrUtil.isNotBlank(destroyMethodName) || !(bean instanceof DisposableBean
                && "destroy".equals(destroyMethodName))) {
            Method method = bean.getClass().getMethod(destroyMethodName);
            if (null == method) {
                throw new BeansException("Could not find a destroy method named " + destroyMethodName
                        + " on bean with name " + beanName);
            }
            method.invoke(bean);
        }
    }
}
