package com.pinc.springframework.context;

import com.pinc.springframework.beans.BeansException;
import com.pinc.springframework.beans.factory.Aware;

/**
 * 实现此接口可以感知到所属的applicationContext
 */
public interface ApplicationContextAware extends Aware {

    void setApplicationContext(ApplicationContext applicationContext) throws BeansException;
}
