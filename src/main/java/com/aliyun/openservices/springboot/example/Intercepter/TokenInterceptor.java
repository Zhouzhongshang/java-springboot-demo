package com.aliyun.openservices.springboot.example.Intercepter;

import com.alibaba.fastjson.JSONObject;
import com.aliyun.openservices.springboot.example.utils.JwtUtils;
import io.jsonwebtoken.Claims;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

@Component
public class TokenInterceptor implements HandlerInterceptor {
    // 自动注入一下
    @Resource
    private JwtUtils jwtUtils;
    // 这个方法是在访问接口之前执行的，我们只需要在这里写验证登陆状态的业务逻辑，就可以在用户调用指定接口之前验证登陆状态了
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 设置返回值属性
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json; charset=utf-8");
        String token = request.getHeader("token");
        PrintWriter out;
        // 在这里写你的判断逻辑 return true是通过拦截器，可以继续访问controller，return false是不通过
        JSONObject res = new JSONObject();
        if (token != null) {
            Claims claims = null;
            try{
                claims = jwtUtils.parseToken(token);
                System.out.println("解析后的claims:"+JSONObject.toJSONString(claims));
            }catch (Exception ignored){
                res.put("state",false);
                res.put("msg","请重新登陆");
                out = response.getWriter();
                out.append(res.toString());
                return false;
            }
            if(claims != null){
                request.setAttribute("user_claims", claims);
                return true;
            }
        }
        res.put("state","false");
        res.put("msg","token is null or wrong");

        out = response.getWriter();
        out.append(res.toString());
        return false;
    }
}