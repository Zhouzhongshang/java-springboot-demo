package com.aliyun.openservices.springboot.example.controller;

import com.alibaba.fastjson.JSON;
import com.aliyun.openservices.springboot.example.controller.quickbiInterface.QuickBiInterface;
import com.aliyun.openservices.springboot.example.controller.response.TokenResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class QuickBiController {

    @Value("${aliyun.accessId}")
    private String accessId;
    @Value("${aliyun.accessKey}")
    private String accessKey;
    @Value("${aliyun.aliyunId}")
    private String aliyunId;
    @Value("${aliyun.validityTime}")
    private Long validityTime;

    @Resource
    private QuickBiInterface quickBiInterface;

    @GetMapping("accessToken")
    public TokenResponse accessToken() {
//        String accessToken = quickBiInterface.accessToken("dc943428-e77c-4954-9e96-9900f9cb5441",
//                "f8e9a479-f24f-4695-b78e-e8d84cae4f97", "admin@yunjiweidian.com:pixuefeng", 240L);

        String accessToken = quickBiInterface.accessToken(accessId, accessKey, aliyunId, validityTime);


        TokenResponse tokenResponse = JSON.parseObject(accessToken, TokenResponse.class);
        return tokenResponse;
    }

}
