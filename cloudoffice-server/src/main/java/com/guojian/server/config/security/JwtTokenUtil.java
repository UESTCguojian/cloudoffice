package com.guojian.server.config.security;

import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author guojian
 * @create 2022-02-18-2022/2/18
 */

@Component
public class JwtTokenUtil {
    public static final String CLAIM_KEY_USERNAME = "sub";
    public static final String CLAIM_KEY_CREATED = "created";

    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.expiration}")
    private Long expiration;

    /**
     * 根据用户信息生成JWT TOKEN
     * @param userDetails
     * @return
     */
    public String generateToken(UserDetails userDetails)
    {
        Map<String, Object> claims = new HashMap<>();
        claims.put(CLAIM_KEY_USERNAME, userDetails.getUsername());
        claims.put(CLAIM_KEY_CREATED, new Date());
        return generateToken(claims);
    }

    /**
     * 根据荷载生成JWT TOKEN
     * @param claims
     * @return
     */
    public String generateToken(Map<String, Object> claims)
    {
        return Jwts.builder()
                .setClaims(claims)
                .setExpiration(generateExpirationDate())
                .signWith(SignatureAlgorithm.HS512, secret)
                .compact();
    }

    /**
     * 根据Token获取到用户名
     * @param Token
     * @return
     */
    public String getUserNameFromToken(String Token)
    {
        String username;
        try {
            Claims claims = getClaimsFromToken(Token);
            username = claims.getSubject();
        } catch (Exception e) {
            username = null;
        }
        return username;
    }

    /**
     * 验证token是否有效
     * @param token
     * @param userDetails
     * @return
     */
    public boolean validateToken(String token, UserDetails userDetails)
    {
        String username = getUserNameFromToken(token);
        return username.equals(userDetails.getUsername()) && !isTokenExpired(token);
    }

    /**
     * 判断Token是否可以被刷新
     * @param token
     * @return
     */
    public boolean canRefresh(String token)
    {
        return !isTokenExpired(token);
    }

    /**
     * 刷新Token
     * @param token
     * @return
     */
    public String refreshToken(String token)
    {
        Claims claims = getClaimsFromToken(token);
        claims.put(CLAIM_KEY_CREATED, new Date());
        return generateToken(claims);
    }

    /**
     * 判断token是否失效
     * @param token
     * @return
     */
    private boolean isTokenExpired(String token)
    {
        Date expiredDate = getExpiredDateFromToken(token);
        return expiredDate.before(new Date());
    }

    /**
     * 从Token中获取失效时间
     * @param token
     * @return
     */
    private Date getExpiredDateFromToken(String token)
    {
        Claims claimsFromToken = getClaimsFromToken(token);
        Date expiration = claimsFromToken.getExpiration();
        return expiration;
    }

    /**
     * 根据Token获取荷载
     * @param token
     * @return
     */
    private Claims getClaimsFromToken(String token)
    {
        Claims claims;
        try {
            claims = Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
        } catch (Exception e) {
            claims = null;
            e.printStackTrace();
        }
        return claims;
    }


    /**
     * 生成Token的失效时间
     * @return
     */
    private Date generateExpirationDate()
    {
        return new Date(System.currentTimeMillis() + expiration*1000);
    }



}
