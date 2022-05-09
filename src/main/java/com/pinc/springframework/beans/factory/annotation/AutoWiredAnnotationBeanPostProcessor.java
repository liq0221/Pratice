package com.pinc.springframework.beans.factory.annotation;

import cn.hutool.core.bean.BeanUtil;
import com.pinc.springframework.beans.BeansException;
import com.pinc.springframework.beans.PropertyValues;
import com.pinc.springframework.beans.factory.BeanFactory;
import com.pinc.springframework.beans.factory.BeanFactoryAware;
import com.pinc.springframework.beans.factory.ConfigurableListableBeanFactory;
import com.pinc.springframework.beans.factory.config.InstantiationAwareBeanPostProcessor;
import com.pinc.springframework.utils.ClassUtils;

import java.lang.reflect.Field;

public class AutoWiredAnnotationBeanPostProcessor implements BeanFactoryAware, InstantiationAwareBeanPostProcessor {


    private ConfigurableListableBeanFactory beanFactory;

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        this.beanFactory = (ConfigurableListableBeanFactory) beanFactory;
    }

    @Override
    public PropertyValues postProcessPropertyValues(PropertyValues propertyValues, Object bean, String beanName) throws BeansException {
        // 处理注解@Value
        Class<?> clazz = bean.getClass();
        clazz = ClassUtils.isCglibProxyClass(clazz) ? clazz.getSuperclass() : clazz;
        Field[] declaredFields = clazz.getDeclaredFields();
        for (Field declaredField : declaredFields) {
            Value valueAnnotation = declaredField.getAnnotation(Value.class);
            if (null != valueAnnotation) {
                String value = valueAnnotation.value();
                value = beanFactory.resolveEmbeddedValue(value);
                BeanUtil.setFieldValue(bean, declaredField.getName(), value);
            }
        }

        // 处理注解@Autowired
        for (Field declaredField : declaredFields) {
            Autowired autowiredAnnotation = declaredField.getAnnotation(Autowired.class);
            if (null != autowiredAnnotation) {
                Class<?> fieldType = declaredField.getType();
                String dependentBeanName = null;
                Qualifier qualifierAnnotation = declaredField.getAnnotation(Qualifier.class);

                Object dependentBean = null;
                if (null != qualifierAnnotation) {
                    dependentBeanName = qualifierAnnotation.value();
                    dependentBean = beanFactory.getBean(dependentBeanName, fieldType);
                } else {
                    dependentBean = beanFactory.getBean(fieldType);
                }
                BeanUtil.setFieldValue(bean, declaredField.getName(), dependentBean);

            }
        }
        return propertyValues;
    }

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        return null;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        return null;
    }

    @Override
    public Object postProcessBeforeInitialization(Class<?> beanClass, String beanName) throws BeansException {
        return null;
    }
}
