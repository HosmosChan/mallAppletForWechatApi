package com.lettuce.management.dao;

import com.lettuce.management.entity.Role;
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

    int count(@Param("params") Map<String, Object> params);

    List<Role> list(@Param("params") Map<String, Object> params, Integer offset, Integer limit);

    Role getByRoleId(Long id);

    void deleteRolePermission(Long id);

    void deleteRoleUser(Long id);

    void delete(Long id);

    void saveRolePermission(Long roleId, List<Long> permissionIds, Long createUserId);
}
