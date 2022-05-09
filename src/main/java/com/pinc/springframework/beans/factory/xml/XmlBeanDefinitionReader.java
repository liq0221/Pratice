package com.pinc.springframework.beans.factory.xml;

import cn.hutool.core.util.StrUtil;
import com.pinc.springframework.beans.BeansException;
import com.pinc.springframework.beans.PropertyValue;
import com.pinc.springframework.beans.factory.config.BeanDefinition;
import com.pinc.springframework.beans.factory.config.BeanReference;
import com.pinc.springframework.beans.factory.support.AbstractBeanDefinitionReader;
import com.pinc.springframework.beans.factory.support.BeanDefinitionRegistry;
import com.pinc.springframework.context.annotation.ClassPathBeanDefinitionScanner;
import com.pinc.springframework.core.io.Resource;
import com.pinc.springframework.core.io.ResourceLoader;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * 解析xml的的beanDefinitionReader
 * 最主要的内容就是解析xml
 * 如果父类没有无参构造方法，子类必须要有有参构造方法
 * 类的创建是从顶部开始的
 * 当你用子类的无参构造函数创建子类对象时，会去先递归调用父类的无参构造方法，
 * 这时候如果某个类的父类没有无参构造方法就会出错啦~
 */
public class XmlBeanDefinitionReader extends AbstractBeanDefinitionReader {


    public XmlBeanDefinitionReader(BeanDefinitionRegistry registry) {
        super(registry);
    }

    public XmlBeanDefinitionReader(BeanDefinitionRegistry registry, ResourceLoader resourceLoader) {
        super(registry, resourceLoader);
    }

    @Override
    public void loadBeanDefinitions(Resource resource) throws BeansException {
        try {
            try (InputStream inputStream = resource.getInputStream()) {
                doLoadBeanDefinitions(inputStream);
            }
        } catch (IOException | ClassNotFoundException | DocumentException e) {
            throw new BeansException("IOException parsing XML document from " + resource, e);
        }
    }

    @Override
    public void loadBeanDefinitions(Resource... resources) throws BeansException {
        for (Resource resource : resources) {
            loadBeanDefinitions(resource);
        }
    }

    @Override
    public void loadBeanDefinitions(String location) throws BeansException {
        ResourceLoader resourceLoader = getResourceLoader();
        Resource resource = resourceLoader.getResource(location);
        loadBeanDefinitions(resource);
    }

    /**
     * 解析xml
     * 最终把读取出来的配置信息，创建成 BeanDefinition 以及 PropertyValue，
     * 最终把完整的 Bean 定义内容注册到 Bean 容器
     * @param inputStream
     */
    protected void doLoadBeanDefinitions(InputStream inputStream) throws ClassNotFoundException, DocumentException {
        SAXReader reader = new SAXReader();
        Document read = reader.read(inputStream);
        Element root = read.getRootElement();

        Element componentScan = root.element("component-scan");
        if (null != componentScan) {
            String scanPath = componentScan.attributeValue("base-package");
            if (StrUtil.isEmpty(scanPath)) {
                throw new BeansException("The value of base-package attribute can not be empty or null");
            }
            scanPackage(scanPath);
        }

        List<Element> beans = root.elements("bean");

        for (Element bean : beans) {
            String id = bean.attributeValue("id");
            String name = bean.attributeValue("name");
            String className = bean.attributeValue("class");
            String initMethodName = bean.attributeValue("init-method");
            String destroyMethodName = bean.attributeValue("destroy-method");
            String scope = bean.attributeValue("scope");
            // 获取class, 方便取类的名称
            Class<?> aClass = Class.forName(className);
            String beanName = StrUtil.isEmpty(id) ? name : id;
            if (StrUtil.isEmpty(beanName)) {
                beanName = StrUtil.lowerFirst(aClass.getSimpleName());
            }
            // 定义bean
            BeanDefinition beanDefinition = new BeanDefinition(aClass);
            beanDefinition.setInitMethodName(initMethodName);
            beanDefinition.setDestroyMethodName(destroyMethodName);
            if (!StrUtil.isEmpty(scope)) {
                beanDefinition.setScope(scope);
            }
            List<Element> properties = bean.elements("property");
            for (Element property : properties) {
                // 读取property属性
                String attrName = property.attributeValue("name");
                String attrValue = property.attributeValue("value");
                String ref = property.attributeValue("ref");

                // 填充属性
                Object value = StrUtil.isEmpty(ref) ? attrValue : new BeanReference(ref);
                PropertyValue propertyValue = new PropertyValue(attrName, value);
                beanDefinition.getPropertyValues().addPropertyValue(propertyValue);

            }
            if (getRegistry().containsBeanDefinition(beanName)) {
                throw new BeansException("Duplicate beanName[" + beanName + "] is not allowed");
            }
            // 注册beanDefinition
            getRegistry().registryBeanDefinition(beanName, beanDefinition);
        }
    }

    private void scanPackage(String scanPath) {
        String[] basePackages = StrUtil.splitToArray(scanPath, ',');
        ClassPathBeanDefinitionScanner scanner = new ClassPathBeanDefinitionScanner(getRegistry());
        scanner.doScan(basePackages);

    }
}
