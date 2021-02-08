package com.aliyun.openservices.springboot.example.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;

/**
 * @program: java-springboot-demo
 * @description: jwt测试
 * @author: 周$
 * @create: 2021-02-08 09:15
 **/
@RunWith(SpringRunner.class)
@SpringBootTest
public class JwtTest {

    /***
     * https://www.cnblogs.com/nihilwater/p/13456182.html
     */
    @Test
    public void testJwt(){
        //version1: 1、创建私钥，这里的私钥是建议随机生成的，这里只是个例子
        String secretString = "12345678901234567890123456789012";
        SecretKey key0 = Keys.hmacShaKeyFor(secretString.getBytes(StandardCharsets.UTF_8));

        //增强版的key
        Key key = Keys.secretKeyFor(SignatureAlgorithm.HS256);

        JwtBuilder jwtBuilder = Jwts.builder().setId("自定义id")
                .setSubject("自定义subject")
                .setIssuedAt(new Date())  // 签发时间
                .signWith(key);  // 签名
        String token = jwtBuilder.compact();

        System.out.println("token:"+token);

        // 2、解析token，这里的key要和私钥是一个
        Claims claims = Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token).getBody();

        System.out.println(claims.getId());
        System.out.println(claims.getSubject());
        System.out.println(claims.getIssuedAt());

        //version2: 加密 解密
        JwtBuilder jwtBuilder1 = Jwts.builder().claim("para1", "value1").claim("参数2", "值2").signWith(key);
        String token1 = jwtBuilder1.compact();
        Claims body = Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token1).getBody();
        System.out.println( body.get("para1"));
        System.out.println(body.get("参数2"));
    }
}
