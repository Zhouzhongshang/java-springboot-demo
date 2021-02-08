package com.aliyun.openservices.springboot.example.data;

import java.io.Serializable;

/**
 * @program: java-springboot-demo
 * @description: 传递用户信息
 * @author: $
 * @create: 2021-01-29 19:05
 **/
public class UserInfo implements Serializable {
    private Integer a = 1;
    private String b = "b";

    public UserInfo() {
    }

    public UserInfo(Integer a, String b) {
        this.a = a;
        this.b = b;
    }

    public Integer getA() {
        return a;
    }

    public void setA(Integer a) {
        this.a = a;
    }

    public String getB() {
        return b;
    }

    public void setB(String b) {
        this.b = b;
    }
}
