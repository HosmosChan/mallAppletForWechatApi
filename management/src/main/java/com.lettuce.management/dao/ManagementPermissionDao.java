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
 *
 * @author Hosmos
 * @description 权限dao层
 * @date 2021年07月08日
 */
public interface ManagementPermissionDao {
    List<Permission> listByUserId(Long userId);

    List<Permission> listAll();

    List<Permission> listParents();

    List<Permission> listByRoleId(Long roleId);

    void savePermission(Permission permission);

    Permission getByPermissionId(Long tid);

    void updatePermission(Permission permission);

    void deleteRolePermission(Long tid);

    void deletePermission(Long tid);

    void deleteByParentId(Long parentId);
}
