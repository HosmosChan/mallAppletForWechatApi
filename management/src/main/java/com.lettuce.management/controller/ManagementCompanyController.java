package com.lettuce.management.controller;

import com.lettuce.common.utils.table.PageTableHandler;
import com.lettuce.common.utils.table.PageTableRequest;
import com.lettuce.common.utils.table.PageTableResponse;
import com.lettuce.management.entity.Company;
import com.lettuce.management.service.ManagementCompanyService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.*;
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
 * 公司配置Controller层
 *
 * @author Hosmos
 * @date 2021年08月28日
 */
@Api(value = "company", tags = "公司配置接口")
@RestController
@RequestMapping("/company")
public class ManagementCompanyController {
    private static final Logger log = LoggerFactory.getLogger("adminLogger");
    @Resource
    private ManagementCompanyService managementCompanyService;

    @GetMapping("/getCompany")
    @ApiOperation(value = "获取公司名和Id")
    public List<Company> getCompany() {
        return managementCompanyService.getCompany();
    }

    @ApiOperation(value = "获取小程序信息列表")
    @GetMapping("/companyList")
    @RequiresPermissions("management:company:query")
    public PageTableResponse getAppletInfoList(PageTableRequest request) {
        return new PageTableHandler(new PageTableHandler.CountHandler() {
            @Override
            public int count(PageTableRequest request) {
                return managementCompanyService.count(request.getParams());
            }
        }, new PageTableHandler.ListHandler() {
            @Override
            public List<Company> list(PageTableRequest request) {
                return managementCompanyService.list(request.getParams(), request.getOffset(), request.getLimit());
            }
        }).handle(request);
    }

    @RequiresPermissions("management:company:add")
    @PostMapping
    @ApiOperation(value = "保存")
    public Company save(@RequestBody Company company) {
        managementCompanyService.save(company);
        return company;
    }

    @RequiresPermissions("management:company:add")
    @PutMapping
    @ApiOperation(value = "修改")
    public Company update(@RequestBody Company company) {
        managementCompanyService.update(company);
        return company;
    }

    @RequiresPermissions("management:company:del")
    @DeleteMapping("/{id}")
    @ApiOperation(value = "删除")
    public void delete(@PathVariable Long id) {
        managementCompanyService.delete(id);
    }


    @GetMapping("/{id}")
    @ApiOperation(value = "根据id获取")
    public Company get(@PathVariable Long id) {
        return managementCompanyService.getById(id);
    }
}
