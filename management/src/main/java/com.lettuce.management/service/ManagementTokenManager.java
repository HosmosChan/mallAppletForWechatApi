package com.lettuce.management.service;

import com.lettuce.management.dto.Token;
import com.lettuce.management.service.impl.EhCacheTokenManager;
import com.lettuce.management.service.impl.RedisTokenManager;
import org.apache.shiro.authc.UsernamePasswordToken;

/**
 * Token管理器
 * 目前提供两种实现
 * 默认基于ehcache，如需更改，使用@Primary注解
 *
 * @author Hosmos
 * @date 2021年07月08日
 * @see EhCacheTokenManager
 * @see RedisTokenManager
 */
public interface ManagementTokenManager {
    /**
     * 保存Token
     *
     * @param token token
     * @return Token
     * @author Hosmos
     * @date 2021年07月08日
     */
    Token saveToken(UsernamePasswordToken token);

    /**
     * 根据token获取凭证
     *
     * @param key token
     * @return UsernamePasswordToken 用户凭证
     * @author Hosmos
     * @date 2021年07月08日
     */
    UsernamePasswordToken getToken(String key);

    /**
     * 删除token
     *
     * @param key token
     * @author Hosmos
     * @date 2021年07月08日
     */
    boolean deleteToken(String key);
}