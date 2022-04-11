package com.pinc.springframework.context;

import com.pinc.springframework.beans.BeansException;

/**
 * ConfigurableApplicationContext 继承自 ApplicationContext，
 * 并提供了 refresh 这个核心方法
 */
public interface ConfigurableApplicationContext extends ApplicationContext{

    /**
     * 刷新容器
     * @throws BeansException
     */
    void refresh() throws BeansException;

    /**
     * 注册虚拟机钩子方法
     */
    void registryShutdownHook();

    /**
     * 手动执行关闭方法
     */
    void close();
}
