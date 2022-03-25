package com.pinc.springframework.core.io;

/**
 * 包装的类资源加载器
 */
public interface ResourceLoader {

    String CLASSPATH_URL_PREFIX = "classpath:";

    /**
     * 定义获取类资源的接口
     * @param location
     * @return
     */
    Resource getResource(String location);

}
