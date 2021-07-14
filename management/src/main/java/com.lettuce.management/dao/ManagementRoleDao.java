package com.lettuce.management.dao;

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
 *
 * @author Hosmos
 * @description 角色dao层
 * @date 2021年07月08日
 */
public interface ManagementRoleDao {
    List<Role> listByUserId(Long userId);

    Role getRole(String name);

    void updateRole(Role role);

    void saveRole(Role role);

    int count(Map<String, Object> params);

    List<Role> list(Map<String, Object> params, Integer offset, Integer limit);

    Role getByRoleId(Long tid);

    void deleteRolePermission(Long tid);

    void deleteRoleUser(Long tid);

    void delete(Long tid);

    void saveRolePermission(Long roleId, List<Long> permissionIds, Long createUserId);
}
