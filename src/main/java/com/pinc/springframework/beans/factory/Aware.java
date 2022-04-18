package com.pinc.springframework.beans.factory;

/**
 * 标记类接口，实现该接口可以被spring容器感知
 * 它就像一个标记一样，方便标记实现类，从而可以获取到实现它的实现类，一般都会使用instanceof去判断。
 */
public interface Aware {
}
