package com.lettuce.management.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.lettuce.management.dto.Token;
import com.lettuce.management.service.ManagementTokenManager;
import org.apache.commons.lang3.time.DateUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * Code is far away from bug with the animal protected
 * 　┏┓　　  ┏┓
 * ┏┻┻━━━┻┻┓
 * ┃　　　　　　　┃
 * ┃　　　━　　　┃
 * ┃　┳┛　┗┳　┃
 * ┃　　　　　　　┃
 * ┃　　　┻　　　┃
 * ┃　　　　　　　┃
 * ┗━┓　　　┏━┛
 * 　　┃　　　┃神兽保佑
 * 　　┃　　　┃代码无BUG！
 * 　　┃　　　┗━━━┓
 * 　　┃　　　　　　　┣┓
 * 　　┃　　　　　　　┣┛
 * 　　┗┳┓┏━┳┓┏┛
 * 　　　┃┫┫　┃┫┫
 * 　　　┗┻┛　┗┻┛
 * redis实现的Token
 *
 * @author Hosmos
 * @date 2021年07月08日
 */
@Service
public class RedisTokenManager implements ManagementTokenManager {
    @Autowired
    private RedisTemplate<String, String> redisTemplate;
    private static final String TOKEN_PREFIX = "tokens:";
    /**
     * token过期秒数
     */
    @Value("${token.expire.seconds}")
    private Integer expireSeconds;

    @Override
    public Token saveToken(UsernamePasswordToken usernamePasswordToken) {
        String key = UUID.randomUUID().toString();
        redisTemplate.opsForValue().set(TOKEN_PREFIX + key, JSONObject.toJSONString(usernamePasswordToken), expireSeconds, TimeUnit.SECONDS);
        return new Token(key, DateUtils.addSeconds(new Date(), expireSeconds));
    }

    @Override
    public UsernamePasswordToken getToken(String key) {
        String json = redisTemplate.opsForValue().get(TOKEN_PREFIX + key);
        if (!StringUtils.isEmpty(json)) {
            UsernamePasswordToken usernamePasswordToken = JSONObject.parseObject(json, UsernamePasswordToken.class);
            return usernamePasswordToken;
        }
        return null;
    }

    @Override
    public boolean deleteToken(String key) {
        key = TOKEN_PREFIX + key;
        if (redisTemplate.hasKey(key)) {
            redisTemplate.delete(key);
            return true;
        }
        return false;
    }
}