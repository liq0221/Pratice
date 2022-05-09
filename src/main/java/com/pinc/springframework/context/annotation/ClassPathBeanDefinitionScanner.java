package com.pinc.springframework.context.annotation;

import cn.hutool.core.util.StrUtil;
import com.pinc.springframework.beans.factory.annotation.AutoWiredAnnotationBeanPostProcessor;
import com.pinc.springframework.beans.factory.config.BeanDefinition;
import com.pinc.springframework.beans.factory.support.BeanDefinitionRegistry;
import com.pinc.springframework.context.stereotype.Component;

import java.util.Set;

public class ClassPathBeanDefinitionScanner extends ClassPathScanningCandidateComponentProvider {

    private BeanDefinitionRegistry registry;

    public ClassPathBeanDefinitionScanner(BeanDefinitionRegistry registry) {
        this.registry = registry;
    }

    public void doScan(String... basePackages) {
        for (String basePackage : basePackages) {
            Set<BeanDefinition> candidates = findCandidateComponents(basePackage);
            for (BeanDefinition beanDefinition : candidates) {
                // 解析bean的作用域
                String scope = resolveBeanScope(beanDefinition);
                if (StrUtil.isNotBlank(scope)) {
                    beanDefinition.setScope(scope);
                }
                registry.registryBeanDefinition(determineBeanName(beanDefinition), beanDefinition);
            }
        }
        registry.registryBeanDefinition("com.pinc.springframework.beans.factory.annotation.AutoWiredAnnotationBeanPostProcessor", new BeanDefinition(AutoWiredAnnotationBeanPostProcessor.class));

    }

    private String determineBeanName(BeanDefinition beanDefinition) {
        Class<?> beanClass = beanDefinition.getBeanClass();
        Component component = beanClass.getAnnotation(Component.class);
        String value = component.value();
        if (StrUtil.isEmpty(value)) {
            return StrUtil.lowerFirst(beanClass.getSimpleName());
        }
        return value;
    }

    private String resolveBeanScope(BeanDefinition beanDefinition) {
        Class<?> beanClass = beanDefinition.getBeanClass();
        Scope scope = beanClass.getAnnotation(Scope.class);
        if (null != scope) {
            return scope.value();
        }
        return StrUtil.EMPTY;
    }
}
