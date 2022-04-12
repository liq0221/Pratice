package com.pinc.springframework.beans;

import com.pinc.springframework.beans.factory.DisposableBean;
import com.pinc.springframework.beans.factory.InitializingBean;

/**
 * 博客：https://bugstack.cn - 沉淀、分享、成长，让自己和他人都能有所收获！
 * 公众号：bugstack虫洞栈
 * Create by 小傅哥(fustack)
 */
public class UserService2 implements InitializingBean, DisposableBean {

    private String uId;
    private String company;
    private String location;
    private UserDao1 userDao;


    public String queryUserInfo() {
        return userDao.queryUserName(uId) + "," + company + "," + location;
    }

    public String getuId() {
        return uId;
    }

    public void setuId(String uId) {
        this.uId = uId;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public UserDao1 getUserDao() {
        return userDao;
    }

    public void setUserDao(UserDao1 userDao) {
        this.userDao = userDao;
    }

    @Override
    public void destroy() throws Exception {
        System.out.println("执行destroy方法");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("执行afterPropertiesSet方法");
    }
}
