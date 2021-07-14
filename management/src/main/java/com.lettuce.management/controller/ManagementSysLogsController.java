package com.lettuce.management.controller;

import com.lettuce.common.utils.table.*;
import com.lettuce.management.entity.SysLogs;
import com.lettuce.management.service.ManagementSysLogsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
 * 日志controller层
 *
 * @author Hosmos
 * @date 2021年07月14日
 */
@Api(tags = "日志")
@RestController
@RequestMapping("/management/logs")
public class ManagementSysLogsController {
    @Resource
    private ManagementSysLogsService managementSysLogsService;

    @GetMapping
    @RequiresPermissions(value = "sys:log:query")
    @ApiOperation(value = "日志列表")
    public PageTableResponse list(PageTableRequest request) {
        return new PageTableHandler(new PageTableHandler.CountHandler() {
            @Override
            public int count(PageTableRequest request) {
                return managementSysLogsService.count(request.getParams());
            }
        }, new PageTableHandler.ListHandler() {
            @Override
            public List<SysLogs> list(PageTableRequest request) {
                return managementSysLogsService.list(request.getParams(), request.getOffset(), request.getLimit());
            }
        }).handle(request);
    }
}
