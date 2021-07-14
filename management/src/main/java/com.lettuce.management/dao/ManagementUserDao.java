package com.lettuce.management.dao;

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
 *
 * @author Hosmos
 * @description 用户dao层
 * @date 2021年07月08日
 */
public interface ManagementUserDao {
    void lastLogin(Long tid);

    User getUser(String username);

    void saveUser(UserDto userDto);

    void updateUser(UserDto userDto);

    void changePassword(Long tid, String passwordEncoder, Long gmtUserId);

    int count(Map<String, Object> params);

    List<User> list(Map<String, Object> params, Integer offset, Integer limit);

    User getByUserId(Long tid);

    void deleteUserRole(Long userId);

    void saveUserRoles(Long userId, List<Long> roleIds, Long creatUserId);
}
