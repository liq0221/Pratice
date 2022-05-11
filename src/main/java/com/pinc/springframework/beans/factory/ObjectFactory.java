package com.pinc.springframework.beans.factory;

import com.pinc.springframework.beans.BeansException;

public interface ObjectFactory<T> {

    T getObject() throws BeansException;
}
