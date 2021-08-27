package com.lettuce.management.controller;

import com.lettuce.common.utils.table.PageTableHandler;
import com.lettuce.common.utils.table.PageTableRequest;
import com.lettuce.common.utils.table.PageTableResponse;
import com.lettuce.management.dto.GoodBaseDto;
import com.lettuce.management.dto.GoodDto;
import com.lettuce.management.entity.GoodBase;
import com.lettuce.management.service.ProductsShownGoodService;
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
 * 产品展示商品controller层
 *
 * @author Hosmos
 * @date 2021年08月24日
 */
@Api(value = "ProductsShownGood", tags = "产品展示商品接口")
@RestController
@RequestMapping("/productsShown/good")
public class ProductsShownGoodController {
    private static final Logger log = LoggerFactory.getLogger("adminLogger");
    @Resource
    private ProductsShownGoodService productsShownGoodService;
    /**
     * 商品查询
     *
     * @param request 查询信息
     * @return PageTableResponse
     * @author Hosmos
     * @date 2021-08-24
     */
    @GetMapping
    @ApiOperation(value = "商品查询")
    @RequiresPermissions("productsShown:good:query")
    public PageTableResponse list(PageTableRequest request) {
        return new PageTableHandler(new PageTableHandler.CountHandler() {
            @Override
            public int count(PageTableRequest request) {
                return productsShownGoodService.count(request.getParams());
            }
        }, new PageTableHandler.ListHandler() {
            @Override
            public List<GoodBaseDto> list(PageTableRequest request) {
                return productsShownGoodService.list(request.getParams(), request.getOffset(), request.getLimit());
            }
        }).handle(request);
    }
    /**
     * 保存商品
     *
     * @param goodDto good dto
     * @return GoodDto
     * @author Hosmos
     * @date 2021-08-27
     */
    @RequiresPermissions("productsShown:good:add")
    @PostMapping
    @ApiOperation(value = "保存")
    public GoodDto save(@RequestBody GoodDto goodDto) {
        productsShownGoodService.save(goodDto);
        return goodDto;
    }
}
