package com.pinc.springframework.context.annotation;

import cn.hutool.core.util.ClassUtil;
import com.pinc.springframework.beans.factory.config.BeanDefinition;
import com.pinc.springframework.context.stereotype.Component;

import java.util.LinkedHashSet;
import java.util.Set;

/**
 * 处理对象扫描装配
 */
public class ClassPathScanningCandidateComponentProvider {

    public Set<BeanDefinition> findCandidateComponents(String basePackage) {
        Set<BeanDefinition> candidates = new LinkedHashSet<>();
        Set<Class<?>> classes = ClassUtil.scanPackageByAnnotation(basePackage, Component.class);
        for (Class<?> aClass : classes) {
            candidates.add(new BeanDefinition(aClass));
        }
        return candidates;
    }
}
