package com.pinc.springframework.context.support;

public class ClassPathXmlApplicationContext extends AbstractXmlApplicationContext{

    private String[] configLocations;

    public ClassPathXmlApplicationContext() {
    }

    public ClassPathXmlApplicationContext(String[] configLocations) {
        this.configLocations = configLocations;
        refresh();
    }

    public ClassPathXmlApplicationContext(String configLocation) {
        this(new String[]{configLocation});
    }

    @Override
    protected String[] getConfigLocations() {
        return this.configLocations;
    }
}
