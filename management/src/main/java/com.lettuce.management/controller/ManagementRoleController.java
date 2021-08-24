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
 * 角色controller层
 *
 * @author Hosmos
 * @date 2021年07月14日
 */
@Api(value = "role", tags = "角色接口")
@RestController
@RequestMapping("/role")
public class ManagementRoleController {
    private static final Logger logger = LoggerFactory.getLogger(ManagementRoleController.class);
    @Resource
    private ManagementRoleService managementRoleService;

    /**
     * 保存角色
     *
     * @param roleDto 角色dto
     * @author Hosmos
     * @date 2021-07-14
     */
    @LogAnnotation
    @PostMapping
    @ApiOperation(value = "保存角色")
    @RequiresPermissions("management:role:add")
    public void saveRole(@RequestBody RoleDto roleDto) {
        managementRoleService.saveRole(roleDto);
    }

    /**
     * 角色列表
     *
     * @param request 返回参数
     * @return PageTableResponse
     * @author Hosmos
     * @date 2021-07-14
     */
    @GetMapping
    @ApiOperation(value = "角色列表")
    @RequiresPermissions("management:role:query")
    public PageTableResponse listRoles(PageTableRequest request) {
        return new PageTableHandler(new PageTableHandler.CountHandler() {
            @Override
            public int count(PageTableRequest request) {
                return managementRoleService.count(request.getParams());
            }
        }, new PageTableHandler.ListHandler() {
            @Override
            public List<Role> list(PageTableRequest request) {
                return managementRoleService.list(request.getParams(), request.getOffset(), request.getLimit());
            }
        }).handle(request);
    }

    /**
     * 根据角色id获取角色
     *
     * @param id role id
     * @return Role
     * @author Hosmos
     * @date 2021-07-14
     */
    @GetMapping("/{id}")
    @ApiOperation(value = "根据角色id获取角色")
    @RequiresPermissions("management:role:query")
    public Role get(@PathVariable Long id) {
        return managementRoleService.getByRoleId(id);
    }

    /**
     * 所有角色
     *
     * @return List<Role>
     * @author Hosmos
     * @date 2021-07-14
     */
    @GetMapping("/all")
    @ApiOperation(value = "所有角色")
    @RequiresPermissions(value = {"management:user:query", "management:role:query"}, logical = Logical.OR)
    public List<Role> roles() {
        return managementRoleService.list(Maps.newHashMap(), null, null);
    }

    /**
     * 根据用户id获取拥有的角色
     *
     * @param userId user id
     * @return List<Role>
     * @author Hosmos
     * @date 2021-07-14
     */
    @GetMapping(params = "userId")
    @ApiOperation(value = "根据用户id获取拥有的角色")
    @RequiresPermissions(value = {"management:user:query", "management:role:query"}, logical = Logical.OR)
    public List<Role> roles(Long userId) {
        return managementRoleService.listByUserId(userId);
    }

    /**
     * 删除角色
     *
     * @param id role id
     * @author Hosmos
     * @date 2021-07-14
     */
    @LogAnnotation
    @DeleteMapping("/{id}")
    @ApiOperation(value = "删除角色")
    @RequiresPermissions(value = {"management:role:del"})
    public void delete(@PathVariable Long id) {
        managementRoleService.deleteRole(id);
    }
}
