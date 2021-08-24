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
 * 角色dao层
 *
 * @author Hosmos
 * @date 2021年07月08日
 */
public interface ManagementRoleDao {
    /**
     * 根据用户id获取角色列表
     *
     * @param userId 用户 id
     * @return List<Role>
     * @author Hosmos
     * @date 2021-08-24
     */
    List<Role> listByUserId(Long userId);

    /**
     * 根据角色名获取角色
     *
     * @param name 角色名
     * @return Role
     * @author Hosmos
     * @date 2021-08-24
     */
    Role getRole(String name);

    /**
     * 更新角色
     *
     * @param role 角色信息
     * @author Hosmos
     * @date 2021-08-24
     */
    void updateRole(Role role);

    /**
     * 保存角色
     *
     * @param role 角色信息
     * @author Hosmos
     * @date 2021-08-24
     */
    void saveRole(Role role);

    /**
     * 获取角色个数
     *
     * @param params 搜索参数
     * @return int
     * @author Hosmos
     * @date 2021-08-24
     */
    int count(@Param("params") Map<String, Object> params);

    /**
     * 获取角色列表
     *
     * @param params 搜索参数
     * @param offset 顺序
     * @param limit  页码
     * @return List<Role>
     * @author Hosmos
     * @date 2021-08-24
     */
    List<Role> list(@Param("params") Map<String, Object> params, Integer offset, Integer limit);

    /**
     * 根据角色id获取角色
     *
     * @param id 角色 id
     * @return Role
     * @author Hosmos
     * @date 2021-08-24
     */
    Role getByRoleId(Long id);

    /**
     * 删除角色的权限
     *
     * @param id 权限 id
     * @author Hosmos
     * @date 2021-08-24
     */
    void deleteRolePermission(Long id);

    /**
     * 删除用户的角色
     *
     * @param id 用户 id
     * @author Hosmos
     * @date 2021-08-24
     */
    void deleteRoleUser(Long id);

    /**
     * 删除角色
     *
     * @param id 角色 id
     * @author Hosmos
     * @date 2021-08-24
     */
    void delete(Long id);

    /**
     * 保存角色的权限
     *
     * @param roleId        角色 id
     * @param permissionIds 权限 id
     * @param createUserId  创建用户 id
     * @author Hosmos
     * @date 2021-08-24
     */
    void saveRolePermission(Long roleId, List<Long> permissionIds, Long createUserId);
}
