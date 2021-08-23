package com.lettuce.management.service.impl;

import com.lettuce.management.dao.ManagementPermissionDao;
import com.lettuce.management.entity.Permission;
import com.lettuce.management.service.ManagementPermissionService;
import com.lettuce.management.utils.UserUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
 * 权限实现层
 *
 * @author Hosmos
 * @date 2021年07月14日
 */
@Service
public class ManagementPermissionServiceImpl implements ManagementPermissionService {
    private static final Logger log = LoggerFactory.getLogger("adminLogger");
    @Autowired
    private ManagementPermissionDao managementPermissionDao;

    @Override
    public List<Permission> listByUserId(Long userId) {
        return managementPermissionDao.listByUserId(userId);
    }

    @Override
    public List<Permission> listAll() {
        return managementPermissionDao.listAll();
    }

    @Override
    public List<Permission> listParents() {
        return managementPermissionDao.listParents();
    }

    @Override
    public List<Permission> listByRoleId(Long roleId) {
        return managementPermissionDao.listByRoleId(roleId);
    }

    @Override
    public void savePermission(Permission permission) {
        permission.setCreateUserId(UserUtil.getCurrentUser().getId());
        managementPermissionDao.savePermission(permission);
        log.debug("新增权限{}", permission.getName());
    }

    @Override
    public Permission getByPermissionId(Long id) {
        return managementPermissionDao.getByPermissionId(id);
    }

    @Override
    public void updatePermission(Permission permission) {
        permission.setGmtUserId(UserUtil.getCurrentUser().getId());
        managementPermissionDao.updatePermission(permission);
    }

    @Override
    public void deletePermission(Long id) {
        managementPermissionDao.deleteRolePermission(id);
        managementPermissionDao.deletePermission(id);
        managementPermissionDao.deleteByParentId(id);
        log.debug("删除权限id:{}", id);
    }
}
