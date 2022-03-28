package com.pinc.springframework.context.support;

import com.pinc.springframework.context.ConfigurableApplicationContext;
import com.pinc.springframework.core.io.DefaultResourceLoader;

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


}
