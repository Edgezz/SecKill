package com.edge.seckill.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;
import java.util.UUID;

/**
 * @author： Edge
 * @date： 2020/2/26
 * @description： TODO
 * @modifiedBy：
 * @version: 1.0
 */
public class JwtUtil {
    private static final String key="skill_jwt";
    /**
     * 生成令牌*/
    public static String createToken(String json){
        //1、指定加密格式
        SignatureAlgorithm algorithm=SignatureAlgorithm.HS256;
        //2、构建JWT
        JwtBuilder builder= Jwts.builder().
                setId(UUID.randomUUID().toString()) //设置唯一id,不能重复 ，防止 重放攻击
                .setIssuedAt(new Date()). //设置签发时间
                //setExpiration(null). //设置令牌的结束时间
                        setSubject(json). //设置主题 一般都是我们自己的数据 令牌对象对应的json
                signWith(algorithm,key); //设置签名算法和对应的秘钥
        //3、返回生成的令牌
        return builder.compact();
    }
    /**
     * 解析令牌*/
    public static String parseToken(String token){
        Claims claims=Jwts.parser().setSigningKey(key).parseClaimsJws(token).getBody();
        if(claims!=null){
            return claims.getSubject();
        }else {
            return null;
        }
    }
}
