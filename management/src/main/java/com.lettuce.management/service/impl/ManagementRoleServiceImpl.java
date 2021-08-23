package com.lettuce.management.service.impl;

import com.lettuce.management.dao.ManagementRoleDao;
import com.lettuce.management.dto.RoleDto;
import com.lettuce.management.entity.Role;
import com.lettuce.management.service.ManagementRoleService;
import com.lettuce.management.utils.UserUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

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
 * 角色实现层
 *
 * @author Hosmos
 * @date 2021年07月14日
 */
@Service
public class ManagementRoleServiceImpl implements ManagementRoleService {
    private static final Logger log = LoggerFactory.getLogger("adminLogger");
    @Autowired
    private ManagementRoleDao managementRoleDao;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void saveRole(RoleDto roleDto) {
        Role role = roleDto;
        if (role.getId() != null) {
            // 修改
            Role r = managementRoleDao.getRole(role.getName());
            if (r != null && !r.getId().equals(role.getId())) {
                throw new IllegalArgumentException(role.getName() + "已存在");
            }
            role.setGmtUserId(UserUtil.getCurrentUser().getId());
            managementRoleDao.updateRole(role);
        } else {
            // 新增
            Role r = managementRoleDao.getRole(role.getName());
            if (r != null) {
                throw new IllegalArgumentException(role.getName() + "已存在");
            }
            role.setCreateUserId(UserUtil.getCurrentUser().getId());
            managementRoleDao.saveRole(role);
            log.debug("新增角色{}", role.getName());
        }
        saveRolePermission(role.getId(), roleDto.getPermissionIds());
    }

    @Override
    public int count(Map<String, Object> params) {
        return managementRoleDao.count(params);
    }

    @Override
    public List<Role> list(Map<String, Object> params, Integer offset, Integer limit) {
        return managementRoleDao.list(params, offset, limit);
    }

    @Override
    public Role getByRoleId(Long id) {
        return managementRoleDao.getByRoleId(id);
    }

    @Override
    public List<Role> listByUserId(Long userId) {
        return managementRoleDao.listByUserId(userId);
    }

    @Override
    public void deleteRole(Long id) {
        managementRoleDao.deleteRolePermission(id);
        managementRoleDao.deleteRoleUser(id);
        managementRoleDao.delete(id);
        log.debug("删除角色id:{}", id);
    }

    private void saveRolePermission(Long roleId, List<Long> permissionIds) {
        managementRoleDao.deleteRolePermission(roleId);
        permissionIds.remove(0L);
        if (!CollectionUtils.isEmpty(permissionIds)) {
            Long createUserId = UserUtil.getCurrentUser().getId();
            managementRoleDao.saveRolePermission(roleId, permissionIds, createUserId);
        }
    }
}
