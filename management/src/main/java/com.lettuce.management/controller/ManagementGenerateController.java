package com.lettuce.management.controller;

import com.lettuce.management.annotation.LogAnnotation;
import com.lettuce.management.dto.BeanField;
import com.lettuce.management.dto.GenerateDetail;
import com.lettuce.management.dto.GenerateInput;
import com.lettuce.management.service.ManagementGenerateService;
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
 * 代码生成controller层
 *
 * @author Hosmos
 * @date 2021年08月23日
 */
@Api(value = "generate", tags = "代码生成")
@RestController
@RequestMapping("/generate")
public class ManagementGenerateController {
    private static final Logger logger = LoggerFactory.getLogger(ManagementLoginController.class);
    @Resource
    private ManagementGenerateService managementGenerateService;

    @ApiOperation("根据表名显示表信息")
    @GetMapping(params = {"tableName"})
    @RequiresPermissions("management:generate:edit")
    public GenerateDetail generateByTableName(String tableName) {
        GenerateDetail detail = new GenerateDetail();
        detail.setBeanName(managementGenerateService.upperFirstChar(tableName));
        List<BeanField> fields = managementGenerateService.listBeanField(tableName);
        detail.setFields(fields);
        return detail;
    }

    @LogAnnotation
    @ApiOperation("生成代码")
    @PostMapping
    @RequiresPermissions("management:generate:edit")
    public void save(@RequestBody GenerateInput input) {
        managementGenerateService.saveCode(input);
    }
}
