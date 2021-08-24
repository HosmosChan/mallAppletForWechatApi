package com.lettuce.management.dao;

import com.lettuce.management.dto.UserDto;
import com.lettuce.management.entity.User;
import org.apache.ibatis.annotations.Param;

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
 * 用户dao层
 *
 * @author Hosmos
 * @date 2021年07月08日
 */
public interface ManagementUserDao {
    /**
     * 保存最后登录时间
     *
     * @param id 用户 id
     * @author Hosmos
     * @date 2021-08-24
     */
    void lastLogin(Long id);

    /**
     * 根据用户名获取用户
     *
     * @param username 用户名
     * @return User
     * @author Hosmos
     * @date 2021-08-24
     */
    User getUser(String username);

    /**
     * 保存用户
     *
     * @param userDto 用户 dto
     * @author Hosmos
     * @date 2021-08-24
     */
    void saveUser(UserDto userDto);

    /**
     * 更新用户
     *
     * @param userDto 用户 dto
     * @author Hosmos
     * @date 2021-08-24
     */
    void updateUser(@Param("user") UserDto userDto);

    /**
     * 更改密码
     *
     * @param id              用户 id
     * @param passwordEncoder 用户加密密码
     * @param gmtUserId       修改用户 id
     * @author Hosmos
     * @date 2021-08-24
     */
    void changePassword(Long id, String passwordEncoder, Long gmtUserId);

    /**
     * 获取用户个数
     *
     * @param params 搜索参数
     * @return int
     * @author Hosmos
     * @date 2021-08-24
     */
    int count(Map<String, Object> params);

    /**
     * 获取用户列表
     *
     * @param params 搜索参数
     * @param offset 顺序
     * @param limit  页码
     * @return List<User>
     * @author Hosmos
     * @date 2021-08-24
     */
    List<User> list(Map<String, Object> params, Integer offset, Integer limit);

    /**
     * 根据用户id获取用户
     *
     * @param id 用户 id
     * @return User
     * @author Hosmos
     * @date 2021-08-24
     */
    User getByUserId(Long id);

    /**
     * 删除用户角色
     *
     * @param userId 用户 id
     * @author Hosmos
     * @date 2021-08-24
     */
    void deleteUserRole(Long userId);

    /**
     * 保存用户角色
     *
     * @param userId      用户 id
     * @param roleIds     角色 id 列表
     * @param creatUserId 创建用户 id
     * @author Hosmos
     * @date 2021-08-24
     */
    void saveUserRoles(Long userId, List<Long> roleIds, Long creatUserId);
}
