package com.lettuce.management.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Lists;
import com.lettuce.management.annotation.LogAnnotation;
import com.lettuce.management.entity.Permission;
import com.lettuce.management.entity.User;
import com.lettuce.management.service.ManagementPermissionService;
import com.lettuce.management.utils.UserUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

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
 * @description 权限controller层
 * @date 2021年07月13日
 */
@Api(value = "permission", tags = "权限接口")
@RestController
@RequestMapping("/permission")
public class ManagementPermissionController {
    private static final Logger logger = LoggerFactory.getLogger(ManagementPermissionController.class);
    @Resource
    private ManagementPermissionService managementPermissionService;

    /**
     * 当前登录用户拥有的权限
     *
     * @return List<Permission>
     * @author Hosmos
     * @date 2021-07-14
     */
    @ApiOperation(value = "当前登录用户拥有的权限")
    @GetMapping("/current")
    public List<Permission> permissionsCurrent() {
        List<Permission> list = UserUtil.getCurrentPermissions();
        if (list == null) {
            User user = UserUtil.getCurrentUser();
            list = managementPermissionService.listByUserId(user.getId());
            UserUtil.setPermissionSession(list);
        }
        final List<Permission> permissions = list.stream().filter(l -> l.getType().equals(1)).collect(Collectors.toList());
        List<Permission> firstLevel = permissions.stream().filter(p -> p.getParentId().equals(0L)).collect(Collectors.toList());
        firstLevel.parallelStream().forEach(p -> {
            setChild(p, permissions);
        });
        return firstLevel;
    }

    /**
     * 权限列表
     *
     * @return List<Permission>
     * @author Hosmos
     * @date 2021-07-14
     */
    @GetMapping
    @ApiOperation(value = "权限列表")
    @RequiresPermissions("management:menu:query")
    public List<Permission> permissionsList() {
        List<Permission> permissionsAll = managementPermissionService.listAll();
        List<Permission> list = Lists.newArrayList();
        setPermissionsList(0L, permissionsAll, list);
        return list;
    }

    /**
     * 所有权限
     *
     * @return JSONArray
     * @author Hosmos
     * @date 2021-07-14
     */
    @GetMapping("/all")
    @ApiOperation(value = "所有权限")
    @RequiresPermissions("management:menu:query")
    public JSONArray permissionsAll() {
        List<Permission> permissionsAll = managementPermissionService.listAll();
        JSONArray array = new JSONArray();
        setPermissionsTree(0L, permissionsAll, array);
        return array;
    }

    /**
     * 父级权限列表
     *
     * @return List<Permission>
     * @author Hosmos
     * @date 2021-07-14
     */
    @GetMapping("/parents")
    @ApiOperation(value = "父级权限")
    @RequiresPermissions("management:menu:query")
    public List<Permission> parentMenu() {
        List<Permission> parents = managementPermissionService.listParents();
        return parents;
    }

    /**
     * 根据角色id获取权限列表
     *
     * @param roleId role id
     * @return List<Permission>
     * @author Hosmos
     * @date 2021-07-14
     */
    @GetMapping(params = "roleId")
    @ApiOperation(value = "根据角色id获取权限列表")
    @RequiresPermissions(value = {"management:menu:query", "management:role:query"}, logical = Logical.OR)
    public List<Permission> listByRoleId(Long roleId) {
        return managementPermissionService.listByRoleId(roleId);
    }

    /**
     * 保存权限
     *
     * @param permission permission实体类
     * @author Hosmos
     * @date 2021-07-14
     */
    @LogAnnotation
    @PostMapping
    @ApiOperation(value = "保存权限")
    @RequiresPermissions("management:menu:add")
    public void savePermission(@RequestBody Permission permission) {
        managementPermissionService.savePermission(permission);
    }

    /**
     * 根据权限id获取权限
     *
     * @param id permission id
     * @return Permission
     * @author Hosmos
     * @date 2021-07-14
     */
    @GetMapping("/{id}")
    @ApiOperation(value = "根据权限id获取权限")
    @RequiresPermissions("management:menu:query")
    public Permission getByPermissionId(@PathVariable Long id) {
        return managementPermissionService.getByPermissionId(id);
    }

    /**
     * 修改权限
     *
     * @param permission permission实体类
     * @author Hosmos
     * @date 2021-07-14
     */
    @LogAnnotation
    @PutMapping
    @ApiOperation(value = "修改权限")
    @RequiresPermissions("management:menu:add")
    public void updatePermission(@RequestBody Permission permission) {
        managementPermissionService.updatePermission(permission);
    }

    /**
     * 校验当前用户的权限
     *
     * @return Set<String>
     * @author Hosmos
     * @date 2021-07-14
     */
    @GetMapping("/owns")
    @ApiOperation(value = "校验当前用户的权限")
    public Set<String> ownsPermission() {
        List<Permission> permissions = UserUtil.getCurrentPermissions();
        if (CollectionUtils.isEmpty(permissions)) {
            return Collections.emptySet();
        }
        return permissions.parallelStream().filter(p -> !StringUtils.isEmpty(p.getPermission())).map(Permission::getPermission).collect(Collectors.toSet());
    }

    /**
     * 根据权限id删除权限
     *
     * @param id permission id
     * @author Hosmos
     * @date 2021-07-14
     */
    @LogAnnotation
    @DeleteMapping("/{id}")
    @ApiOperation(value = "根据权限id删除权限")
    @RequiresPermissions(value = {"management:menu:del"})
    public void deletePermission(@PathVariable Long id) {
        managementPermissionService.deletePermission(id);
    }

    /**
     * 设置子元素
     *
     * @param permission  permission实体类
     * @param permissions permission列表
     * @author Hosmos
     * @date 2021-07-14
     */
    private void setChild(Permission permission, List<Permission> permissions) {
        List<Permission> child = permissions.parallelStream().filter(a -> a.getParentId().equals(permission.getId())).collect(Collectors.toList());
        permission.setChild(child);
        if (!CollectionUtils.isEmpty(child)) {
            child.parallelStream().forEach(c -> {
                //递归设置子元素，多级菜单支持
                setChild(c, permissions);
            });
        }
    }

    /**
     * 设置权限列表
     *
     * @param id   permission id
     * @param permissionsAll 欲添加permission列表
     * @param list           原有permission列表
     * @author Hosmos
     * @date 2021-07-14
     */
    private void setPermissionsList(Long id, List<Permission> permissionsAll, List<Permission> list) {
        for (Permission permission : permissionsAll) {
            if (permission.getParentId().equals(id)) {
                list.add(permission);
                if (permissionsAll.stream().filter(p -> p.getParentId().equals(permission.getId())).findAny() != null) {
                    setPermissionsList(permission.getId(), permissionsAll, list);
                }
            }
        }
    }

    /**
     * 设置菜单树
     *
     * @param id   permission id
     * @param permissionsAll 所有permission列表
     * @param array          用JSONArray显示permission
     * @author Hosmos
     * @date 2021-07-14
     */
    private void setPermissionsTree(Long id, List<Permission> permissionsAll, JSONArray array) {
        for (Permission per : permissionsAll) {
            if (per.getParentId().equals(id)) {
                String string = JSONObject.toJSONString(per);
                JSONObject parent = (JSONObject) JSONObject.parse(string);
                array.add(parent);
                if (permissionsAll.stream().filter(p -> p.getParentId().equals(per.getId())).findAny() != null) {
                    JSONArray child = new JSONArray();
                    parent.put("child", child);
                    setPermissionsTree(per.getId(), permissionsAll, child);
                }
            }
        }
    }
}
