package com.aliyun.openservices.springboot.example.controller.quickbiInterface;

import com.dtflys.forest.annotation.DataParam;
import com.dtflys.forest.annotation.Request;


public interface QuickBiInterface {

    @Request(url = "https://das.base.shuju.aliyun.com/api/ac3rdservice/token.json" ,type = "get")
    String accessToken(@DataParam("accessId") String accessId, @DataParam("accessKey") String accessKey, @DataParam("aliyunId") String aliyunId , @DataParam("validityTime") Long validityTime);
}
