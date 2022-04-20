package com.pinc.springframework.context.event;

import com.pinc.springframework.context.ApplicationContext;
import com.pinc.springframework.context.ApplicationEvent;

/**
 * 定义事件的抽象类
 */
public abstract class ApplicationContextEvent extends ApplicationEvent {

    /**
     * Constructs a prototypical Event.
     *
     * @param source The object on which the Event initially occurred.
     * @throws IllegalArgumentException if source is null.
     */
    public ApplicationContextEvent(Object source) {
        super(source);
    }

    public final ApplicationContext getApplicationContext() {
        return (ApplicationContext) getSource();
    }

}
