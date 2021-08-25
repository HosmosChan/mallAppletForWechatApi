package com.lettuce.management.controller;

import com.lettuce.common.utils.table.PageTableHandler;
import com.lettuce.common.utils.table.PageTableRequest;
import com.lettuce.common.utils.table.PageTableResponse;
import com.lettuce.management.entity.Category;
import com.lettuce.management.service.ProductsShownCategoryService;
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
 * 产品展示分类controller层
 *
 * @author Hosmos
 * @date 2021年08月24日
 */
@Api(value = "ProductsShownCategory", tags = "产品展示分类接口")
@RestController
@RequestMapping("/productsShown/category")
public class ProductsShownCategoryController {
    private static final Logger log = LoggerFactory.getLogger("adminLogger");
    @Resource
    private ProductsShownCategoryService productsShownCategoryService;

    /**
     * 分类查询
     *
     * @param request 查询信息
     * @return PageTableResponse
     * @author Hosmos
     * @date 2021-08-24
     */
    @GetMapping
    @ApiOperation(value = "分类查询")
    @RequiresPermissions("productsShown:category:query")
    public PageTableResponse listFiles(PageTableRequest request) {
        return new PageTableHandler(new PageTableHandler.CountHandler() {
            @Override
            public int count(PageTableRequest request) {
                return productsShownCategoryService.count(request.getParams());
            }
        }, new PageTableHandler.ListHandler() {
            @Override
            public List<Category> list(PageTableRequest request) {
                return productsShownCategoryService.list(request.getParams(), request.getOffset(), request.getLimit());
            }
        }).handle(request);
    }

    /**
     * 保存分类
     *
     * @param category 分类实体类
     * @return Category
     * @author Hosmos
     * @date 2021-08-25
     */
    @RequiresPermissions("productsShown:category:add")
    @PostMapping
    @ApiOperation(value = "保存")
    public Category save(@RequestBody Category category) {
        Category d = productsShownCategoryService.getCategoryByName(category.getCategoryName(), category.getAppId());
        if (d != null) {
            throw new IllegalArgumentException("分类已存在");
        }
        productsShownCategoryService.save(category);
        return category;
    }

    /**
     * 修改分类
     *
     * @param category 分类实体类
     * @return Category
     * @author Hosmos
     * @date 2021-08-25
     */
    @RequiresPermissions("productsShown:category:add")
    @PutMapping
    @ApiOperation(value = "修改")
    public Category update(@RequestBody Category category) {
        productsShownCategoryService.update(category);
        return category;
    }

    /**
     * 根据id获取分类
     *
     * @param id 分类 id
     * @return Category
     * @author Hosmos
     * @date 2021-08-25
     */
    @GetMapping("/{id}")
    @ApiOperation(value = "根据id获取分类")
    public Category get(@PathVariable Long id) {
        return productsShownCategoryService.getById(id);
    }

    /**
     * 删除分类
     *
     * @param id 分类 id
     * @author Hosmos
     * @date 2021-08-25
     */
    @RequiresPermissions("productsShown:category:del")
    @DeleteMapping("/{id}")
    @ApiOperation(value = "删除")
    public void delete(@PathVariable Long id) {
        productsShownCategoryService.delete(id);
    }
}
