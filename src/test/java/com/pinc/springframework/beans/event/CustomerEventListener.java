package com.pinc.springframework.beans.event;

import com.pinc.springframework.context.ApplicationListener;

import java.util.Date;

public class CustomerEventListener implements ApplicationListener<CustomerEvent> {

    @Override
    public void onApplicationEvent(CustomerEvent event) {
        System.out.println("收到：" + event.getSource() + "消息；时间：" + new Date());
        System.out.println("消息：" + event.getId() + ":" + event.getMessage());
    }
}
