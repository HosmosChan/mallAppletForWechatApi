package com.lettuce.management.service;

import com.lettuce.management.dto.UserDto;
import com.lettuce.management.entity.User;

import java.util.List;
import java.util.Map;

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
 * 用户业务层
 *
 * @author Hosmos
 * @date 2021年07月08日
 */
public interface ManagementUserService {
    /**
     * 通过用户名获取用户信息
     *
     * @param username 用户名
     * @return User
     * @author Hosmos
     * @date 2021-07-10
     */
    User getUser(String username);

    /**
     * 保存用户信息
     *
     * @param userDto userDto实体类
     * @return User
     * @author Hosmos
     * @date 2021-07-10
     */
    User saveUser(UserDto userDto);

    /**
     * 用户密码签名
     *
     * @param credentials 用户输入的密码
     * @param salt        盐
     * @return String
     * @author Hosmos
     * @date 2021-07-10
     */
    String passwordEncoder(String credentials, String salt);

    /**
     * 更新用户信息
     *
     * @param userDto userDto实体类
     * @return User
     * @author Hosmos
     * @date 2021-07-14
     */
    User updateUser(UserDto userDto);

    /**
     * 修改密码
     *
     * @param username    用户名
     * @param oldPassword 旧密码
     * @param newPassword 新密码
     * @author Hosmos
     * @date 2021-07-14
     */
    void changePassword(String username, String oldPassword, String newPassword);

    /**
     * 用户信息列表个数，list中页码计数
     *
     * @param params 搜索参数
     * @return int
     * @author Hosmos
     * @date 2021-07-14
     */
    int count(Map<String, Object> params);

    /**
     * 通过搜索参数获取用户信息列表
     *
     * @param params 搜索参数
     * @param offset 每页起始序列
     * @param limit  每页显示个数
     * @return List<User>
     * @author Hosmos
     * @date 2021-07-14
     */
    List<User> list(Map<String, Object> params, Integer offset, Integer limit);

    /**
     * 通过用户id获取用户信息
     *
     * @param id user id
     * @return User
     * @author Hosmos
     * @date 2021-07-14
     */
    User getByUserId(Long id);
}
