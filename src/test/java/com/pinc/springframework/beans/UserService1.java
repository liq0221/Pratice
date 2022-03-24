package com.pinc.springframework.beans;

public class UserService1 {

    private String id;

    private UserDao userDao;

    public UserService1() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void queryUser() {
        System.out.println("用户为：" + userDao.queryUserName(id));
    }
}
