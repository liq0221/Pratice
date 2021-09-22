package com.pinc.practice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;

import java.io.IOException;
import java.util.Properties;

@SpringBootApplication
public class PracticeApplication {

    public static void main(String[] args) {
        ResourceLoader resourceLoader = new DefaultResourceLoader();
        Resource resource = resourceLoader.getResource("git.properties");
        Properties properties = new Properties();
        try {
            properties.load(resource.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("读取当前应用git.properties文件异常", e);
        }
        System.out.println(properties.getProperty("git.branch"));
        SpringApplication.run(PracticeApplication.class, args);
    }

}
