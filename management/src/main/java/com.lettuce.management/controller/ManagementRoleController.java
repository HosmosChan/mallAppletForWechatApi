package com.lettuce.management.controller;

import com.google.common.collect.Maps;
import com.lettuce.common.utils.table.*;
import com.lettuce.management.annotation.LogAnnotation;
import com.lettuce.management.dto.RoleDto;
import com.lettuce.management.entity.Role;
import com.lettuce.management.service.ManagementRoleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
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
 * @description 角色controller层
 * @date 2021年07月14日
 */
@Api(value = "permissions", tags = "角色接口")
@RestController
@RequestMapping("/management/roles")
public class ManagementRoleController {
    private static final Logger logger = LoggerFactory.getLogger(ManagementRoleController.class);
    @Resource
    private ManagementRoleService managementRoleService;

    /**
     * @param roleDto
     * @description 保存角色
     * @author Hosmos
     * @date 2021-07-14
     */
    @LogAnnotation
    @PostMapping
    @ApiOperation(value = "保存角色")
    @RequiresPermissions("sys:role:add")
    public void saveRole(@RequestBody RoleDto roleDto) {
        managementRoleService.saveRole(roleDto);
    }

    /**
     * @param request
     * @return PageTableResponse
     * @description 角色列表
     * @author Hosmos
     * @date 2021-07-14
     */
    @GetMapping
    @ApiOperation(value = "角色列表")
    @RequiresPermissions("sys:role:query")
    public PageTableResponse listRoles(PageTableRequest request) {
        return new PageTableHandler(new PageTableHandler.CountHandler() {
            @Override
            public int count(PageTableRequest request) {
                return managementRoleService.count(request.getParams());
            }
        }, new PageTableHandler.ListHandler() {
            @Override
            public List<Role> list(PageTableRequest request) {
                List<Role> list = managementRoleService.list(request.getParams(), request.getOffset(), request.getLimit());
                return list;
            }
        }).handle(request);
    }

    /**
     * @param tid role id
     * @return Role
     * @description 根据角色id获取角色
     * @author Hosmos
     * @date 2021-07-14
     */
    @GetMapping("/{tid}")
    @ApiOperation(value = "根据角色id获取角色")
    @RequiresPermissions("sys:role:query")
    public Role get(@PathVariable Long tid) {
        return managementRoleService.getByRoleId(tid);
    }

    /**
     * @return List<Role>
     * @description 所有角色
     * @author Hosmos
     * @date 2021-07-14
     */
    @GetMapping("/all")
    @ApiOperation(value = "所有角色")
    @RequiresPermissions(value = {"sys:user:query", "sys:role:query"}, logical = Logical.OR)
    public List<Role> roles() {
        return managementRoleService.list(Maps.newHashMap(), null, null);
    }

    /**
     * @param userId user id
     * @return List<Role>
     * @description 根据用户id获取拥有的角色
     * @author Hosmos
     * @date 2021-07-14
     */
    @GetMapping(params = "userId")
    @ApiOperation(value = "根据用户id获取拥有的角色")
    @RequiresPermissions(value = {"sys:user:query", "sys:role:query"}, logical = Logical.OR)
    public List<Role> roles(Long userId) {
        return managementRoleService.listByUserId(userId);
    }

    /**
     * @param tid role id
     * @description 删除角色
     * @author Hosmos
     * @date 2021-07-14
     */
    @LogAnnotation
    @DeleteMapping("/{tid}")
    @ApiOperation(value = "删除角色")
    @RequiresPermissions(value = {"sys:role:del"})
    public void delete(@PathVariable Long tid) {
        managementRoleService.deleteRole(tid);
    }
}
