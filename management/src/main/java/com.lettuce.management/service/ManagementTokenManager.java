package com.lettuce.management.service;

import com.lettuce.management.dto.Token;
import com.lettuce.management.service.impl.EhCacheTokenManager;
import com.lettuce.management.service.impl.RedisTokenManager;
import org.apache.shiro.authc.UsernamePasswordToken;

/**
 * Token管理器<br>
 * 目前提供两种实现<br>
 * 默认基于ehcache，如需更改，使用@Primary注解
 *
 * @author chenhuayang
 * @see EhCacheTokenManager
 * @see RedisTokenManager
 */
public interface ManagementTokenManager {
    /**
     * 保存Token
     *
     * @param token token
     * @return Token
     */
    Token saveToken(UsernamePasswordToken token);

    /**
     * 根据token获取凭证
     *
     * @param key token
     * @return UsernamePasswordToken 用户凭证
     */
    UsernamePasswordToken getToken(String key);

    /**
     * 删除token
     *
     * @param key token
     */
    boolean deleteToken(String key);
}