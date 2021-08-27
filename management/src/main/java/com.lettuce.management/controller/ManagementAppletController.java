package com.lettuce.management.controller;

import com.lettuce.common.utils.table.PageTableHandler;
import com.lettuce.common.utils.table.PageTableRequest;
import com.lettuce.common.utils.table.PageTableResponse;
import com.lettuce.management.dto.AppletDto;
import com.lettuce.management.entity.Company;
import com.lettuce.management.service.ManagementAppletService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
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
 * 小程序配置Controller层
 *
 * @author Hosmos
 * @date 2021年08月26日
 */
@Api(value = "applet", tags = "小程序接口")
@RestController
@RequestMapping("/applet")
public class ManagementAppletController {
    private static final Logger log = LoggerFactory.getLogger("adminLogger");
    @Resource
    private ManagementAppletService managementAppletService;

    @ApiOperation(value = "根据当前用户获取appId")
    @GetMapping("/appId")
    public String getAppIdByUserId() {
        return managementAppletService.getAppIdByUserId();
    }

    @ApiOperation(value = "获取小程序信息列表")
    @GetMapping("/appletList")
    @RequiresPermissions("management:applet:query")
    public PageTableResponse getAppletInfoList(PageTableRequest request) {
        return new PageTableHandler(new PageTableHandler.CountHandler() {
            @Override
            public int count(PageTableRequest request) {
                return managementAppletService.count(request.getParams());
            }
        }, new PageTableHandler.ListHandler() {
            @Override
            public List<AppletDto> list(PageTableRequest request) {
                return managementAppletService.list(request.getParams(), request.getOffset(), request.getLimit());
            }
        }).handle(request);
    }

    @RequiresPermissions("management:applet:add")
    @PostMapping
    @ApiOperation(value = "保存")
    public AppletDto save(@RequestBody AppletDto appletDto) {
        AppletDto d = managementAppletService.getByAppId(appletDto.getAppId());
        if (d != null) {
            throw new IllegalArgumentException("该appId已存在");
        }
        managementAppletService.save(appletDto);
        return appletDto;
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "根据id获取")
    public AppletDto get(@PathVariable Long id) {
        return managementAppletService.getById(id);
    }

    @RequiresPermissions("management:applet:add")
    @PutMapping
    @ApiOperation(value = "修改")
    public AppletDto update(@RequestBody AppletDto appletDto) {
        managementAppletService.update(appletDto);
        return appletDto;
    }

    @RequiresPermissions("management:applet:del")
    @DeleteMapping("/{id}")
    @ApiOperation(value = "删除")
    public void delete(@PathVariable Long id) {
        managementAppletService.delete(id);
    }

    @GetMapping("/getCompany")
    @ApiOperation(value = "获取公司名和Id")
    public Company getCompany() {
        return managementAppletService.getCompany();
    }

    @GetMapping("/getCompanyByAppId")
    @ApiOperation(value = "根据app id获取公司名和Id")
    public Company getCompanyByAppId(String appId) {
        return managementAppletService.getCompanyByAppId(appId);
    }
}
