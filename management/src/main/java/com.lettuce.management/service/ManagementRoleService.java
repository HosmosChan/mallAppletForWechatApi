package com.lettuce.management.service;

import com.lettuce.management.dto.RoleDto;
import com.lettuce.management.entity.Role;

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
 * 角色业务层
 *
 * @author Hosmos
 * @date 2021年07月14日
 */
public interface ManagementRoleService {
    /**
     * 保存角色
     *
     * @param roleDto role-permission
     * @author Hosmos
     * @date 2021-07-14
     */
    void saveRole(RoleDto roleDto);

    /**
     * 角色信息列表个数，list中页码计数
     *
     * @param params 搜索参数
     * @return int
     * @author Hosmos
     * @date 2021-07-14
     */
    int count(Map<String, Object> params);

    /**
     * 通过搜索参数获取角色信息列表
     *
     * @param params 搜索参数
     * @param offset 每页起始序列
     * @param limit  每页显示个数
     * @return List<Role>
     * @author Hosmos
     * @date 2021-07-14
     */
    List<Role> list(Map<String, Object> params, Integer offset, Integer limit);

    /**
     * 通过角色id获取角色信息
     *
     * @param id role id
     * @return Role
     * @author Hosmos
     * @date 2021-07-14
     */
    Role getByRoleId(Long id);

    /**
     * 通过用户id获取角色信息列表
     *
     * @param userId user id
     * @return List<Role>
     * @author Hosmos
     * @date 2021-07-14
     */
    List<Role> listByUserId(Long userId);

    /**
     * 通过角色id删除角色信息
     *
     * @param id role id
     * @author Hosmos
     * @date 2021-07-14
     */
    void deleteRole(Long id);
}
