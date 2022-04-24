package com.pinc.springframework.context;

import java.util.EventObject;

/**
 * 具备发送事件的抽象类
 * 编译器看到我们在Father类里写了有参构造法方法，它就会认为，
 * 我们不想让子类在回溯的时候走默认的无参构造方法这条路【super(); 此路不通】，
 * 但是我们又没告诉给子类，它应该走哪条路（你给了有参就是告诉我不要走无参，但也没告诉我走有参，那走哪？），
 * 所以会报错。 所以，若父类没有提供无参构造方法，但是提供了有参构造方法，就要在子类的无参构造方法里，
 * 显式的加上调用父类的有参构造方法语句。
 */
public abstract class ApplicationEvent extends EventObject {

    /**
     * Constructs a prototypical Event.
     *
     * @param source The object on which the Event initially occurred.
     * @throws IllegalArgumentException if source is null.
     */
    public ApplicationEvent(Object source) {
        super(source);
    }
}
