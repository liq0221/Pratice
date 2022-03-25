package com.pinc.springframework.core.io;

import org.springframework.util.Assert;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * 默认的类资源加载器
 */
public class DefaultResourceLoader implements ResourceLoader{

    @Override
    public Resource getResource(String location) {
        Assert.notNull(location, "location must not be null");

        if (location.startsWith(CLASSPATH_URL_PREFIX)) {
            return new ClassPathResource(location.substring(CLASSPATH_URL_PREFIX.length()));
        } else {
            try {
                return new UrlPathResource(new URL(location));
            } catch (MalformedURLException e) {
                return new FileSystemResource(location);
            }
        }

    }
}
