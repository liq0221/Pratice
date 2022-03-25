package com.pinc.springframework.beans.factory;

/**
 * spring源码中提供了可以获取父类beanFactory的方法，属于是一种扩展工厂的层次子接口
 * Sub-interface implemented by bean factories that can be part of a hierarchy.
 */
public interface HierarchicalBeanFactory extends BeanFactory{
}
