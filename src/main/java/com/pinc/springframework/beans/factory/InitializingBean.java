package com.pinc.springframework.beans.factory;

public interface InitializingBean {

    /**
     * Bean处理了普通属性后调用
     * @throws Exception
     */
    void afterPropertiesSet() throws Exception;
}
