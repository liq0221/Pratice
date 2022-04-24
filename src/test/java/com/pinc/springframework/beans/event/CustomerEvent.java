package com.pinc.springframework.beans.event;

import com.pinc.springframework.context.ApplicationEvent;

public class CustomerEvent extends ApplicationEvent {

    private Long id;

    private String message;

    public CustomerEvent(Object source, Long id, String message) {
        super(source);
        this.id = id;
        this.message = message;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
