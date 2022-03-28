package com.pinc.springframework.context.support;

import com.pinc.springframework.beans.BeansException;
import com.pinc.springframework.beans.factory.ConfigurableListableBeanFactory;
import com.pinc.springframework.beans.factory.config.BeanFactoryPostProcessor;
import com.pinc.springframework.beans.factory.config.BeanPostProcessor;
import com.pinc.springframework.context.ConfigurableApplicationContext;
import com.pinc.springframework.core.io.DefaultResourceLoader;

import java.util.Map;

/**
 * AbstractApplicationContext 继承 DefaultResourceLoader 是为了处理 spring.xml 配置资源的加载。
 * 之后是在 refresh() 定义实现过程，包括：
 * 1.创建 BeanFactory，并加载 BeanDefinition
 * 2.获取 BeanFactory
 * 3.在 Bean 实例化之前，执行 BeanFactoryPostProcessor
 * 4.BeanPostProcessor 需要提前于其他 Bean 对象实例化之前执行注册操作
 * 5.提前实例化单例Bean对象
 * 另外把定义出来的抽象方法，refreshBeanFactory()、getBeanFactory() 由后面的继承此抽象类的其他抽象类实现。
 */
public abstract class AbstractApplicationContext extends DefaultResourceLoader implements ConfigurableApplicationContext {


    @Override
    public void refresh() throws BeansException {
        // 创建beanFactory,并加载beanDefinition
        refreshBeanFactory();

        // 获取beanFactory
        ConfigurableListableBeanFactory beanFactory = getBeanFactory();

        // 在实例化bean之前 执行beanFactoryPostProcessor
        invokeBeanFactoryPostProcessor(beanFactory);

        // beanPostProcessor需要提前于其他bean对象实例化之前执行注册操作
        registryBeanPostProcessors(beanFactory);

        // 提前实例化bean对象
        beanFactory.preInstantiateSingletons();
    }

    private void registryBeanPostProcessors(ConfigurableListableBeanFactory beanFactory) {
        Map<String, BeanPostProcessor> beanPostProcessorMap = beanFactory.getBeansOfType(BeanPostProcessor.class);
        for (BeanPostProcessor beanPostProcessor : beanPostProcessorMap.values()) {
            beanFactory.addBeanPostProcessors(beanPostProcessor);
        }
    }

    private void invokeBeanFactoryPostProcessor(ConfigurableListableBeanFactory beanFactory) {
        Map<String, BeanFactoryPostProcessor> beanFactoryPostProcessorMap
                = beanFactory.getBeansOfType(BeanFactoryPostProcessor.class);
        for (BeanFactoryPostProcessor beanFactoryPostProcessor : beanFactoryPostProcessorMap.values()) {
            beanFactoryPostProcessor.postProcessorBeanFactory(beanFactory);
        }
    }

    protected abstract ConfigurableListableBeanFactory getBeanFactory();

    protected abstract void refreshBeanFactory();

    @Override
    public Object getBean(String beanName) throws BeansException {
        return getBeanFactory().getBean(beanName);
    }

    @Override
    public Object getBean(String beanName, Object... args) throws BeansException {
        return getBeanFactory().getBean(beanName, args);
    }

    @Override
    public <T> T getBean(String name, Class<T> requiredType) throws BeansException {
        return getBeanFactory().getBean(name, requiredType);
    }

    @Override
    public <T> Map<String, T> getBeansOfType(Class<T> type) throws BeansException {
        return getBeanFactory().getBeansOfType(type);
    }

    @Override
    public String[] getBeanDefinitionNames() {
        return getBeanFactory().getBeanDefinitionNames();
    }


}
