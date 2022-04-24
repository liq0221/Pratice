package com.pinc.springframework.beans;

import cn.hutool.core.io.IoUtil;
import com.pinc.springframework.aop.AdvisedSupport;
import com.pinc.springframework.aop.TargetSource;
import com.pinc.springframework.aop.aspectj.AspectJExpressionPointCut;
import com.pinc.springframework.aop.framework.Cglib2AopProxy;
import com.pinc.springframework.aop.framework.JdkDynamicAopProxy;
import com.pinc.springframework.beans.event.CustomerEvent;
import com.pinc.springframework.beans.factory.config.BeanDefinition;
import com.pinc.springframework.beans.factory.config.BeanReference;
import com.pinc.springframework.beans.factory.support.DefaultListableBeanFactory;
import com.pinc.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import com.pinc.springframework.context.support.ClassPathXmlApplicationContext;
import com.pinc.springframework.core.io.DefaultResourceLoader;
import com.pinc.springframework.core.io.Resource;

import java.io.IOException;
import java.io.InputStream;

public class Test {

    @org.junit.jupiter.api.Test
    public void test_instantiation() {
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();

        BeanDefinition beanDefinition = new BeanDefinition(UserService.class);
        beanFactory.registryBeanDefinition("userService", beanDefinition);

        UserService userService = (UserService) beanFactory.getBean("userService");
//        userService.queryUser();

    }

    @org.junit.jupiter.api.Test
    public void test_property() {

        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();

        // 注册userDao
        beanFactory.registryBeanDefinition("userDao", new BeanDefinition(UserDao.class));

        // 设置属性
        PropertyValues propertyValues = new PropertyValues();
        propertyValues.addPropertyValue(new PropertyValue("id", "10001"));
        propertyValues.addPropertyValue(new PropertyValue("userDao", new BeanReference("userDao")));

        // 注册userService
        beanFactory.registryBeanDefinition("userService1", new BeanDefinition(UserService1.class, propertyValues));

        UserService1 userService = (UserService1) beanFactory.getBean("userService1");
        userService.queryUser();

    }

    @org.junit.jupiter.api.Test
    public void test_resource() throws IOException {
//        Resource resource = new DefaultResourceLoader().getResource("classpath:application.properties");
//        InputStream inputStream = resource.getInputStream();
//        System.out.println(IoUtil.readUtf8(inputStream));

        Resource resource = new DefaultResourceLoader().getResource("src/main/resources/application.properties");
        InputStream inputStream = resource.getInputStream();
        System.out.println(IoUtil.readUtf8(inputStream));
    }

    @org.junit.jupiter.api.Test
    public void test_xmlResource() {
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();

        XmlBeanDefinitionReader xmlBeanDefinitionReader = new XmlBeanDefinitionReader(beanFactory);
        xmlBeanDefinitionReader.loadBeanDefinitions("classpath:spring.xml");

        UserService1 userService = (UserService1) beanFactory.getBean("userService", UserService1.class);
        userService.queryUser();
    }

    @org.junit.jupiter.api.Test
    public void test_postProcessor() {
        ClassPathXmlApplicationContext classPathXmlApplicationContext =
                new ClassPathXmlApplicationContext("classpath:springPostProcessor.xml");
        UserService userService = (UserService)classPathXmlApplicationContext.getBean("userService");
        System.out.println(userService.queryUserInfo());
    }

    @org.junit.jupiter.api.Test
    public void test_initAndDestroy() {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring-init-destroy.xml");
        applicationContext.registryShutdownHook();

        UserService2 userService = applicationContext.getBean("userService", UserService2.class);
        String info = userService.queryUserInfo();
        System.out.println("执行结果" + info);

    }

    @org.junit.jupiter.api.Test
    public void test_aware() {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring-aware.xml");
        applicationContext.registryShutdownHook();

        UserService3 userService = applicationContext.getBean("userService", UserService3.class);
        String info = userService.queryUserInfo();
        System.out.println("执行结果" + info);
        System.out.println("ApplicationContextAware:" + userService.getApplicationContext());
//        System.out.println("BeanFactoryAware:" + userService.getBeanFactory());
    }

    @org.junit.jupiter.api.Test
    public void test_prototype() {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring-factoryBean.xml");
        applicationContext.registryShutdownHook();

        UserService4 userService1 = applicationContext.getBean("userService", UserService4.class);
        UserService4 userService2 = applicationContext.getBean("userService", UserService4.class);
        System.out.println(userService1);
        System.out.println(userService2);

        System.out.println(userService1 + "十六进制哈希:" + Integer.toHexString(userService1.hashCode()));

    }

    @org.junit.jupiter.api.Test
    public void test_proxy() {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring-factoryBean.xml");
        applicationContext.registryShutdownHook();

        UserService4 userService = applicationContext.getBean("userService", UserService4.class);
        System.out.println(userService.queryUserInfo());
    }

    @org.junit.jupiter.api.Test
    public void test_event() {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring-event.xml");
        applicationContext.publishEvent(new CustomerEvent(applicationContext, 111110000L, "频传"));

        applicationContext.registryShutdownHook();
    }

    @org.junit.jupiter.api.Test
    public void test_dynamic() {
        // 目标对象
        IUserService userService = new UserService5();
        // 组装代理信息
        AdvisedSupport advisedSupport = new AdvisedSupport();
        advisedSupport.setMethodInterceptor(new UserInfoInterceptor());
        advisedSupport.setTargetSource(new TargetSource(userService));
        advisedSupport.setMethodMatcher(new AspectJExpressionPointCut(
                "execution(* com.pinc.springframework.beans.IUserService.*(..))"));

        IUserService jdkDynamicAopProxy = (IUserService) new JdkDynamicAopProxy(advisedSupport).getProxy();
        System.out.println("测试结果:" + jdkDynamicAopProxy.queryUserInfo());

        IUserService cglibAopProxy = (IUserService) new Cglib2AopProxy(advisedSupport).getProxy();
        System.out.println("测试结果:" + cglibAopProxy.register("频传"));

    }
    
}
