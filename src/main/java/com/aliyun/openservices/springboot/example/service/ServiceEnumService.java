package com.aliyun.openservices.springboot.example.service;

import org.springframework.stereotype.Service;

/**
 * @program: java-springboot-demo
 * @description: 测试业务枚举
 * @author: zzs
 * @create: 2021-03-23 21:28
 **/
@Service
public class ServiceEnumService {
    /**
     * 方法回调
     * @param args
     */
    public void callBack(String args) {
        System.out.println("args = "+args);
    }
}
