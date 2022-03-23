package com.pinc.springframework.beans;

public class UserService {

    private String userName;

    public UserService() {
    }

    public UserService(String userName) {
        this.userName = userName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void queryUser() {
        System.out.println("该用户为：" + userName);
    }
}
