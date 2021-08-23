package com.lettuce.management.service;

import com.lettuce.management.entity.Permission;

import java.util.List;

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
 * 权限业务层
 *
 * @author Hosmos
 * @date 2021年07月14日
 */
public interface ManagementPermissionService {
    /**
     * 通过用户id获取权限信息列表
     *
     * @param userId user id
     * @return List<Permission>
     * @author Hosmos
     * @date 2021-07-14
     */
    List<Permission> listByUserId(Long userId);

    /**
     * 获取所有权限信息列表
     *
     * @return List<Permission>
     * @author Hosmos
     * @date 2021-07-14
     */
    List<Permission> listAll();

    /**
     * 获取父级权限下的所有子权限信息列表
     *
     * @return List<Permission>
     * @author Hosmos
     * @date 2021-07-14
     */
    List<Permission> listParents();

    /**
     * 通过角色id获取权限信息列表
     *
     * @param roleId role id
     * @return List<Permission>
     * @author Hosmos
     * @date 2021-07-14
     */
    List<Permission> listByRoleId(Long roleId);

    /**
     * 保存权限信息
     *
     * @param permission permission实体类
     * @author Hosmos
     * @date 2021-07-14
     */
    void savePermission(Permission permission);

    /**
     * 通过权限id获取权限信息
     *
     * @param id permission id
     * @return Permission
     * @author Hosmos
     * @date 2021-07-14
     */
    Permission getByPermissionId(Long id);

    /**
     * 更新权限信息
     *
     * @param permission permission实体类
     * @author Hosmos
     * @date 2021-07-14
     */
    void updatePermission(Permission permission);

    /**
     * 通过权限id删除权限信息
     *
     * @param id permission id
     * @author Hosmos
     * @date 2021-07-14
     */
    void deletePermission(Long id);
}
