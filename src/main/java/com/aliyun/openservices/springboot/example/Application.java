package com.aliyun.openservices.springboot.example;

import com.dtflys.forest.annotation.ForestScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@ForestScan(basePackages = "com.aliyun.openservices.springboot.example.controller.quickbiInterface")
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}
