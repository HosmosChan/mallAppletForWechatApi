package com.lettuce.management.controller;

import com.lettuce.common.utils.table.*;
import com.lettuce.management.annotation.LogAnnotation;
import com.lettuce.management.dto.UserDto;
import com.lettuce.management.entity.User;
import com.lettuce.management.service.ManagementUserService;
import com.lettuce.management.utils.UserUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
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
 * 用户controller层
 *
 * @author Hosmos
 * @date 2021年07月14日
 */
@Api(tags = "用户")
@RestController
@RequestMapping("/management/user")
public class ManagementUserController {
    private static final Logger log = LoggerFactory.getLogger("adminLogger");
    @Resource
    private ManagementUserService managementUserService;
    @LogAnnotation
    @PostMapping("/register")
    @ApiOperation(value = "保存用户")
    @RequiresPermissions("sys:user:add")
    public User saveUser(@RequestBody UserDto userDto) {
        User u = managementUserService.getUser(userDto.getUsername());
        if (u != null) {
            throw new IllegalArgumentException(userDto.getUsername() + "已存在");
        }
        return managementUserService.saveUser(userDto);
    }

    @LogAnnotation
    @PutMapping
    @ApiOperation(value = "修改用户")
    @RequiresPermissions("sys:user:add")
    public User updateUser(@RequestBody UserDto userDto) {
        return managementUserService.updateUser(userDto);
    }

    @LogAnnotation
    @ApiOperation(value = "修改头像")
    @PutMapping(params = "headImgUrl")
    public void updateHeadImgUrl(String headImgUrl) {
        User user = UserUtil.getCurrentUser();
        UserDto userDto = new UserDto();
        BeanUtils.copyProperties(user, userDto);
        userDto.setHeadImgUrl(headImgUrl);
        managementUserService.updateUser(userDto);
        log.debug("{}修改了头像", user.getUsername());
    }

    @LogAnnotation
    @PutMapping("/{username}")
    @ApiOperation(value = "修改密码")
    @RequiresPermissions("sys:user:password")
    public void changePassword(@PathVariable String username, String oldPassword, String newPassword) {
        managementUserService.changePassword(username, oldPassword, newPassword);
    }

    @GetMapping
    @ApiOperation(value = "用户列表")
    @RequiresPermissions("sys:user:query")
    public PageTableResponse listUsers(PageTableRequest request) {
        return new PageTableHandler(new PageTableHandler.CountHandler() {
            @Override
            public int count(PageTableRequest request) {
                return managementUserService.count(request.getParams());
            }
        }, new PageTableHandler.ListHandler() {
            @Override
            public List<User> list(PageTableRequest request) {
                List<User> list = managementUserService.list(request.getParams(), request.getOffset(), request.getLimit());
                return list;
            }
        }).handle(request);
    }

    @ApiOperation(value = "当前登录用户")
    @GetMapping("/current")
    public User currentUser() {
        return UserUtil.getCurrentUser();
    }

    @ApiOperation(value = "根据用户id获取用户")
    @GetMapping("/{tid}")
    @RequiresPermissions("sys:user:query")
    public User user(@PathVariable Long tid) {
        return managementUserService.getByUserId(tid);
    }
}
