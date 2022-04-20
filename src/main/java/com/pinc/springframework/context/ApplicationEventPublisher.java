package com.pinc.springframework.context;

import com.pinc.springframework.context.ApplicationEvent;

public interface ApplicationEventPublisher {

    void publishEvent(ApplicationEvent event);
}
