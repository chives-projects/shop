package com.shop.shopweb.config.token;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.shop.shopweb.po.User;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @description: token 下发
 * @author: csc
 * @create: 2019/12/23 10:15
 */
@Service
public class TokenService {

    public String getToken(User user) {
        Date start = new Date();
        long currentTime = System.currentTimeMillis() + 60 * 60 * 1000;//一小时有效时间
        System.out.println(user.getPassword());
        Date end = new Date(currentTime);
        String token = JWT.create().withAudience(user.getUserId()).withIssuedAt(start).withExpiresAt(end)
                .sign(Algorithm.HMAC256(user.getPassword()));
        return token;
    }
}