package com.southwind.springboottest.utils;

import io.jsonwebtoken.*;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.Date;
import java.util.UUID;

//@CrossOrigin(origins = "*")

//如果使用login接口则需要JwtUtil
public class JwtUtil {
    private static long time = 1000*60*60*24;  //一天,为了实现验证一天有效
    private static String signature = "admin";

    public static String createToken(){
        JwtBuilder jwtBuilder = Jwts.builder();
        String jwtToken = jwtBuilder
                //header
                .setHeaderParam("typ", "JWT")
                .setHeaderParam("alg", "HS256")
                //payload
                .claim("username", "admin")
                .claim("role", "admin")
                .setSubject("admin-test")
                .setExpiration(new Date(System.currentTimeMillis()+time))
                .setId(UUID.randomUUID().toString())
                //signature
                .signWith(SignatureAlgorithm.HS256, signature)
                .compact();
        return jwtToken;


    }


}
