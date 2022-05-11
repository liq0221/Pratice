package com.pinc.springframework.beans;

import com.pinc.springframework.beans.factory.annotation.Autowired;
import com.pinc.springframework.context.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component("userDao")
public class UserDao {

    @Autowired
    private UserService7 service7;

    private static Map<String, String> hashMap = new HashMap<>();

    static {
        hashMap.put("10001", "小傅哥");
        hashMap.put("10002", "八杯水");
        hashMap.put("10003", "阿毛");
    }

    public String queryUserName(String uId) {
        return hashMap.get(uId);
    }

}
