package com.aliyun.openservices.springboot.example.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.aliyun.openservices.shade.com.google.common.collect.Maps;
import com.aliyun.openservices.springboot.example.enums.InventoryEnum;
import com.aliyun.openservices.springboot.example.utils.JwtUtils;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;

/**
 * @program: java-springboot-demo
 * @description: 模拟登陆生成token
 * @author: Zhou$
 * @create: 2021-02-08 10:32
 **/
@RestController
public class LoginService {

    @Autowired
    private ServiceEnumService serviceEnumService;

    @Resource
    private JwtUtils jwtUtils;

    /**
     * 登录
     * @param id
     * @param name
     * @return
     */
    @RequestMapping("/login")
    public String login(String id,String name){
        HashMap<String, Object> authorityMap = Maps.newHashMap();
        authorityMap.put("id","/api/put");
        return  jwtUtils.generateToken(id, name, authorityMap);
    }

    /**
     * 模拟接口
     * @param request
     * @return
     */
    @RequestMapping("get")
    public String get(HttpServletRequest request){
        Claims token = jwtUtils.parseToken(request.getHeader("token"));
        System.out.println(JSONObject.toJSONString(token));
        System.out.println(JSON.toJSONString(token));
        return JSON.toJSONString(token);
    }

    /**
     * 枚举中的策略模式
     */
    @GetMapping("testEnum")
    public void testEnum(){
        InventoryEnum.SAVE.serviceInventory(serviceEnumService);
        InventoryEnum.SUBMIT.serviceInventory(serviceEnumService);
    }

}
