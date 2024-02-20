package com.example.SpringBootMybatisPlus.common;

import com.alibaba.fastjson2.JSON;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;
import java.util.Date;
import java.util.UUID;

/*
JWT
 */
@Component
public class JwtUtil {
    // 有效期
    private static final long JWT_EXPIRE = 30 * 60 * 1000L;  //半小时
    // 令牌秘钥
    private static final String JWT_KEY = "123456";

    //创建Token
    public String createToken(Object data) {
        // 当前时间
        long currentTime = System.currentTimeMillis();
        // 过期时间
        long expTime = currentTime + JWT_EXPIRE;
        // 构建jwt
        JwtBuilder builder = Jwts.builder()
                .setId(UUID.randomUUID() + "")
                .setSubject(JSON.toJSONString(data))//设置令牌的持有者
                .setIssuer("system")//设置JWT的签发者
                .setIssuedAt(new Date(currentTime)) //设置发放的时间
                //encodeSecret(JWT_KEY)签名密钥
                .signWith(SignatureAlgorithm.HS256, encodeSecret(JWT_KEY))
                .setExpiration(new Date(expTime));//设置过期时间
        return builder.compact();
    }

    private SecretKey encodeSecret(String key) {
        byte[] encode = Base64.getEncoder().encode(key.getBytes());
        SecretKeySpec aes = new SecretKeySpec(encode, 0, encode.length, "AES");//使用AES对数据进行加密和解密
        return aes;
    }

    //解析token
    public Claims parseToken(String token) {
        try {
            Claims body = Jwts.parser()
                    .setSigningKey(encodeSecret(JWT_KEY))
                    .parseClaimsJws(token)
                    .getBody();
            return body;
        } catch (Exception e) {
            throw new RuntimeException("解析token出错");
        }
    }

    //解析token
    public <T> T parseToken(String token, Class<T> clazz) {
        try {
            Claims body = Jwts.parser()
                    .setSigningKey(encodeSecret(JWT_KEY))
                    .parseClaimsJws(token)
                    .getBody();
            return JSON.parseObject(body.getSubject(), clazz);
        } catch (Exception e) {
            throw new RuntimeException("解析token出错");
        }
    }
}