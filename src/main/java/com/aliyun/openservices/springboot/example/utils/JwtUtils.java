package com.aliyun.openservices.springboot.example.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;
import java.util.Map;

public class JwtUtils {
    //密钥
    private final String secretString = "12345678901234567890123456789012";
    //key 包含算法
    private final SecretKey key = Keys.hmacShaKeyFor(secretString.getBytes(StandardCharsets.UTF_8));

    //增强版的key
    Key enhanceKey = Keys.secretKeyFor(SignatureAlgorithm.HS256);
    /***
     * 生成token
     * @param userId
     * @param userNick
     * @param other
     * @return
     *
     * eg:
     * String JwtToken = Jwts.builder()
     *                 // 第一部分 ：jwt头 ，typ表示令牌类型JWT,alg表示签名的算法，默认是Hs2565 ，然后进行base64URL算法
     *                 .setHeaderParam("typ", "JWT")
     *                 .setHeaderParam("alg", "HS256")
     *                 // 第二部分 有效载荷
     *                 .setSubject("qiyun-user")
     *                 .setIssuedAt(new Date())
     *                 // 设置过期时间
     *                 .setExpiration(new Date(System.currentTimeMillis() + EXPIRE))
     *                 // claim 中的信息可以更改 ，因为返回的就是claim中的数据
     *                 .claim("id", id)
     *                 .claim("nickname", nickname)
     *                 // 第三部分 ，签名哈希 ，对以上两部分数据签名 然后通过HS256算法生成哈希，其中APP_SECRET为自己定义的，存放在服务端，
     *                 .signWith(SignatureAlgorithm.HS256, APP_SECRET)
     *                 .compact();
     *         return JwtToken;
     */
    public String generateToken(String userId, String userNick, Map<String, Object> other) {
        // 设置有效时间 10s方便测试
        long period = 1000000;
        JwtBuilder jwtBuilder = Jwts.builder()
                .setClaims(other) // 使用setClaims可以将Map数据进行加密，必须放在其他变量之前
                .setId(userId)
                .setSubject(userNick)
                .setExpiration(new Date(System.currentTimeMillis() + period)) // 设置有效期
                .signWith(enhanceKey);
        return jwtBuilder.compact();
    }

    /***
     * 解析token
     * @param token
     * @return
     */
    public Claims parseToken(String token){
        return Jwts.parserBuilder().setSigningKey(enhanceKey).build().parseClaimsJws(token).getBody();
    }
}