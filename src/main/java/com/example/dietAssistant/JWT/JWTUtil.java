package com.example.dietAssistant.JWT;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.Map;

public class JWTUtil {

    public static String creatJWT(Map<String, Object> map, Long ttl, String secretKey) {
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;

        Long time = System.currentTimeMillis() + ttl;
        Date date = new Date(time);

        return Jwts.builder()
                .setClaims(map)
                .signWith(signatureAlgorithm, secretKey.getBytes(StandardCharsets.UTF_8))
                .setExpiration(date)
                .compact();
    }

    public static Claims parseJWT(String secretkey, String token) {
        Claims claims = Jwts.parser()
                // 设置签名的秘钥
                .setSigningKey(secretkey.getBytes(StandardCharsets.UTF_8))
                // 设置需要解析的jwt
                .parseClaimsJws(token).getBody();
        return claims;
    }
}
