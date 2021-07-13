package com.lettuce.management.service;

import com.lettuce.management.dto.UserDto;
import com.lettuce.management.entity.User;

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
 *
 * @author Hosmos
 * @description
 * @date 2021年07月08日
 */
public interface ManagementUserService {
    /**
     * @description 通过用户名获取用户信息
     * @author Hosmos
     * @date 2021-07-10
     * @param username
     * @return User
     */
    User getUser(String username);
    /**
     * @description 保存用户信息
     * @author Hosmos
     * @date 2021-07-10
     * @param userDto
     * @return User
     */
    User saveUser(UserDto userDto);
    /**
     * @description 用户密码签名
     * @author Hosmos
     * @date 2021-07-10
     * @param credentials
     * @param salt
     * @return String
     */
    String passwordEncoder(String credentials, String salt);
}
