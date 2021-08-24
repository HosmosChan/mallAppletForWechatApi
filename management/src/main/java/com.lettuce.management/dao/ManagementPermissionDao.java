package com.lettuce.management.dao;

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
 * 权限dao层
 *
 * @author Hosmos
 * @date 2021年07月08日
 */
public interface ManagementPermissionDao {
    /**
     * 根据用户id获取权限列表
     *
     * @param userId 用户 id
     * @return List<Permission>
     * @author Hosmos
     * @date 2021-08-24
     */
    List<Permission> listByUserId(Long userId);

    /**
     * 获取所有权限
     *
     * @return List<Permission>
     * @author Hosmos
     * @date 2021-08-24
     */
    List<Permission> listAll();

    /**
     * 获取权限父级目录
     *
     * @return List<Permission>
     * @author Hosmos
     * @date 2021-08-24
     */
    List<Permission> listParents();

    /**
     * 根据角色id获取权限列表
     *
     * @param roleId 角色 id
     * @return List<Permission>
     * @author Hosmos
     * @date 2021-08-24
     */
    List<Permission> listByRoleId(Long roleId);

    /**
     * 保存权限
     *
     * @param permission 权限信息
     * @author Hosmos
     * @date 2021-08-24
     */
    void savePermission(Permission permission);

    /**
     * 根据权限id获取权限信息
     *
     * @param id 权限 id
     * @return Permission
     * @author Hosmos
     * @date 2021-08-24
     */
    Permission getByPermissionId(Long id);

    /**
     * 更新权限
     *
     * @param permission 权限信息
     * @author Hosmos
     * @date 2021-08-24
     */
    void updatePermission(Permission permission);

    /**
     * 删除角色的权限
     *
     * @param id 权限 id
     * @author Hosmos
     * @date 2021-08-24
     */
    void deleteRolePermission(Long id);

    /**
     * 删除权限
     *
     * @param id 权限 id
     * @author Hosmos
     * @date 2021-08-24
     */
    void deletePermission(Long id);

    /**
     * 根据父级id删除权限
     *
     * @param parentId 父级id
     * @author Hosmos
     * @date 2021-08-24
     */
    void deleteByParentId(Long parentId);
}
