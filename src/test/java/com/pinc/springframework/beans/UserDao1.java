package com.pinc.springframework.beans;

import java.util.HashMap;
import java.util.Map;

public class UserDao1 {

    private static Map<String, String> hashMap = new HashMap<>();

    public void initMethod() {
        System.out.println("执行init-method");
        hashMap.put("10001", "小傅哥");
        hashMap.put("10002", "八杯水");
        hashMap.put("10003", "阿毛");
    }

    public void destroyMethod() {
        System.out.println("执行destroy-method");
        hashMap.clear();
    }

    public String queryUserName(String uId) {
        return hashMap.get(uId);
    }

}
