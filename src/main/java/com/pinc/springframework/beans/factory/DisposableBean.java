package com.pinc.springframework.beans.factory;

public interface DisposableBean {

    /**
     * 销毁方法
     * @throws Exception
     */
    void destroy() throws Exception;
}
