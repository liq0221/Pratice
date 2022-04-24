package com.pinc.springframework.beans;

public interface IUserDao {

    String queryUserName(String userId);

    String queryUserName(String userId, String company);

}
