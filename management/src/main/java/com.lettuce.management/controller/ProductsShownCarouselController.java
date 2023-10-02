package com.lettuce.management.controller;

import com.lettuce.common.utils.table.PageTableHandler;
import com.lettuce.common.utils.table.PageTableRequest;
import com.lettuce.common.utils.table.PageTableResponse;
import com.lettuce.management.entity.Category;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
 * 产品展示轮播图controller层
 *
 * @author Hosmos
 * @date 2021年11月04日
 */
@Api(value = "ProductsShownCarousel", tags = "产品展示轮播图接口")
@RestController
@RequestMapping("/productsShown/carousel")
public class ProductsShownCarouselController {
    /*private static final Logger log = LoggerFactory.getLogger("adminLogger");
    @Resource
    private ProductsShownCarouselService productsShownCarouselService;

    @GetMapping
    @ApiOperation(value = "轮播图查询")
    @RequiresPermissions("productsShown:carousel:query")
    public PageTableResponse list(PageTableRequest request) {
        return new PageTableHandler(new PageTableHandler.CountHandler() {
            @Override
            public int count(PageTableRequest request) {
                return productsShownCarouselService.count(request.getParams());
            }
        }, new PageTableHandler.ListHandler() {
            @Override
            public List<Category> list(PageTableRequest request) {
                return productsShownCarouselService.list(request.getParams(), request.getOffset(), request.getLimit());
            }
        }).handle(request);
    }*/
}
