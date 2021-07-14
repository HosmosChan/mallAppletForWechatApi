package com.lettuce.management.controller;

import com.lettuce.common.utils.table.*;
import com.lettuce.management.entity.Dict;
import com.lettuce.management.service.ManagementDictService;
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
 * 字典controller层
 *
 * @author Hosmos
 * @date 2021年07月13日
 */
@Api(value = "dict", tags = "字典接口")
@RestController
@RequestMapping("/management/dict")
public class ManagementDictController {
    private static final Logger logger = LoggerFactory.getLogger(ManagementDictController.class);
    @Resource
    private ManagementDictService managementDictService;

    /**
     * 保存字典
     *
     * @param dict 字典实体类
     * @return Dict
     * @author Hosmos
     * @date 2021-07-14
     */
    @RequiresPermissions("dict:add")
    @PostMapping
    @ApiOperation(value = "保存")
    public Dict save(@RequestBody Dict dict) {
        Dict d = managementDictService.getByTypeAndKey(dict.getType(), dict.getKey());
        if (d != null) {
            throw new IllegalArgumentException("类型和key已存在");
        }
        managementDictService.save(dict);
        return dict;
    }

    @GetMapping("/{tid}")
    @ApiOperation(value = "根据tid获取")
    public Dict get(@PathVariable Long tid) {
        return managementDictService.getByTid(tid);
    }

    @RequiresPermissions("dict:add")
    @PutMapping
    @ApiOperation(value = "修改")
    public Dict update(@RequestBody Dict dict) {
        managementDictService.update(dict);
        return dict;
    }

    @RequiresPermissions("dict:query")
    @GetMapping(params = {"start", "length"})
    @ApiOperation(value = "列表")
    public PageTableResponse list(PageTableRequest request) {
        return new PageTableHandler(new PageTableHandler.CountHandler() {
            @Override
            public int count(PageTableRequest request) {
                return managementDictService.count(request.getParams());
            }
        }, new PageTableHandler.ListHandler() {
            @Override
            public List<Dict> list(PageTableRequest request) {
                return managementDictService.list(request.getParams(), request.getOffset(), request.getLimit());
            }
        }).handle(request);
    }

    @RequiresPermissions("dict:del")
    @DeleteMapping("/{tid}")
    @ApiOperation(value = "删除")
    public void delete(@PathVariable Long tid) {
        managementDictService.delete(tid);
    }

    @GetMapping(params = "type")
    public List<Dict> listByType(String type) {
        return managementDictService.listByType(type);
    }
}
