package com.lettuce.management.service.impl;


import com.lettuce.management.dto.Token;
import com.lettuce.management.service.ManagementTokenManager;
import net.sf.ehcache.Cache;
import net.sf.ehcache.Element;
import org.apache.commons.lang3.time.DateUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.cache.ehcache.EhCacheManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.UUID;

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
 * EhCache实现的Token, 默认采用此实现
 *
 * @author Hosmos
 * @date 2021年07月08日
 */
@Primary
@Service
public class EhCacheTokenManager implements ManagementTokenManager {
    @Autowired
    private EhCacheManager cacheManager;
    /**
     * token过期秒数
     */
    @Value("${token.expire.seconds}")
    private Integer expireSeconds;

    @Override
    public Token saveToken(UsernamePasswordToken usernamePasswordToken) {
        Cache cache = cacheManager.getCacheManager().getCache("login_user_tokens");
        String key = UUID.randomUUID().toString();
        Element element = new Element(key, usernamePasswordToken);
        element.setTimeToLive(expireSeconds);
        cache.put(element);
        return new Token(key, DateUtils.addSeconds(new Date(), expireSeconds));
    }

    @Override
    public UsernamePasswordToken getToken(String key) {
        Cache cache = cacheManager.getCacheManager().getCache("login_user_tokens");
        Element element = cache.get(key);
        if (element != null) {
            return (UsernamePasswordToken) element.getObjectValue();
        }
        return null;
    }

    @Override
    public boolean deleteToken(String key) {
        Cache cache = cacheManager.getCacheManager().getCache("login_user_tokens");
        return cache.remove(key);
    }
}