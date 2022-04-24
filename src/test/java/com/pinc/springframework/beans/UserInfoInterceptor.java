package com.pinc.springframework.beans;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

public class UserInfoInterceptor implements MethodInterceptor {


    @Override
    public Object invoke(MethodInvocation methodInvocation) throws Throwable {
        long start = System.currentTimeMillis();

        try {
            return methodInvocation.proceed();
        } finally {
            System.out.println("监控 -begin AOP");
            System.out.println("方法名称:" + methodInvocation.getMethod());
            System.out.println("执行耗时:" + (System.currentTimeMillis() - start) + "ms");
            System.out.println("监控结束\r\n");
        }
    }
}
