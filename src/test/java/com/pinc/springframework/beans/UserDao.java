package com.pinc.springframework.beans;

import java.util.HashMap;
import java.util.Map;

public class UserDao {

    private static Map<String, String> map = new HashMap<>();

    static {
        map.put("10001", "小傅哥");
        map.put("10002", "八杯水");
        map.put("10003", "阿毛");
    }

    public String queryUserName(String id) {
        return map.get(id);
    }

}
