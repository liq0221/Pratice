package com.pinc.springframework.beans.factory.xml;

import cn.hutool.core.util.StrUtil;
import cn.hutool.core.util.XmlUtil;
import com.pinc.springframework.beans.BeansException;
import com.pinc.springframework.beans.PropertyValue;
import com.pinc.springframework.beans.factory.config.BeanDefinition;
import com.pinc.springframework.beans.factory.config.BeanReference;
import com.pinc.springframework.beans.factory.support.AbstractBeanDefinitionReader;
import com.pinc.springframework.beans.factory.support.BeanDefinitionRegistry;
import com.pinc.springframework.core.io.Resource;
import com.pinc.springframework.core.io.ResourceLoader;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import java.io.IOException;
import java.io.InputStream;

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
            try (InputStream inputStream  = resource.getInputStream()) {
                doLoadBeanDefinitions(inputStream);
            }
        } catch (IOException | ClassNotFoundException e) {
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
    protected void doLoadBeanDefinitions(InputStream inputStream) throws ClassNotFoundException {
        Document document = XmlUtil.readXML(inputStream);
        Element element = document.getDocumentElement();
        NodeList childNodes = element.getChildNodes();

        for (int i = 0; i < childNodes.getLength(); i++) {
            if (!(childNodes.item(i) instanceof Element)) {
                continue;
            }
            if (!"bean".equals(childNodes.item(i).getNodeName())) {
                continue;
            }
            Element bean = (Element) childNodes.item(i);
            String id = bean.getAttribute("id");
            String name = bean.getAttribute("name");
            String className = bean.getAttribute("class");
            // 获取class, 方便取类的名称
            Class<?> aClass = Class.forName(className);
            String beanName = StrUtil.isEmpty(id) ? name : id;
            if (StrUtil.isEmpty(beanName)) {
                beanName = StrUtil.lowerFirst(aClass.getSimpleName());
            }
            // 定义bean
            BeanDefinition beanDefinition = new BeanDefinition(aClass);
            for (int j = 0; j < bean.getChildNodes().getLength(); j++) {
                if (!(bean.getChildNodes().item(j) instanceof Element)) {
                    continue;
                }
                if (!"property".equals(bean.getChildNodes().item(j).getNodeName())) {
                    continue;
                }
                // 读取property属性
                Element property = (Element) bean.getChildNodes().item(j);
                String attrName = property.getAttribute("name");
                String attrValue = property.getAttribute("value");
                String ref = property.getAttribute("ref");
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
}
