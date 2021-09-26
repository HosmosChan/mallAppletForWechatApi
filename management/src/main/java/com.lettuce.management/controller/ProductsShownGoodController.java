package com.lettuce.management.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.lettuce.common.utils.table.PageTableHandler;
import com.lettuce.common.utils.table.PageTableRequest;
import com.lettuce.common.utils.table.PageTableResponse;
import com.lettuce.management.annotation.LogAnnotation;
import com.lettuce.management.dto.GoodBaseDto;
import com.lettuce.management.dto.GoodDto;
import com.lettuce.management.dto.GoodInfoListDto;
import com.lettuce.management.entity.DeliverWay;
import com.lettuce.management.entity.GoodBase;
import com.lettuce.management.entity.GoodInfo;
import com.lettuce.management.service.ProductsShownGoodService;
import io.swagger.annotations.*;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
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

    /**
     * 所有配送方式
     *
     * @return JSONArray
     * @author Hosmos
     * @date 2021-08-27
     */
    @GetMapping("/deliverWay/all")
    @ApiOperation(value = "所有配送方式")
    public JSONArray deliverWayAll() {
        List<DeliverWay> deliverWayAll = productsShownGoodService.listAllDeliverWay();
        JSONArray array = new JSONArray();
        setDeliverWayTree(deliverWayAll, array);
        return array;
    }

    @GetMapping("/getGoodByCategoryId")
    @ApiOperation(value = "根據分類Id獲取商品")
    public List<GoodBase> getGoodByCategoryId(Long categoryId) {
        return productsShownGoodService.getGoodByCategoryId(categoryId);
    }

    /**
     * 商品查询
     *
     * @param request 查询詳情信息圖片信息
     * @return PageTableResponse
     * @author Hosmos
     * @date 2021-09-24
     */
    @GetMapping("/goodInfo")
    @ApiOperation(value = "商品詳情信息圖片查询")
    @RequiresPermissions("productsShown:good:query")
    public PageTableResponse goodInfoList(PageTableRequest request) {
        return new PageTableHandler(new PageTableHandler.CountHandler() {
            @Override
            public int count(PageTableRequest request) {
                return productsShownGoodService.goodInfoCount(request.getParams());
            }
        }, new PageTableHandler.ListHandler() {
            @Override
            public List<GoodInfoListDto> list(PageTableRequest request) {
                return productsShownGoodService.goodInfoList(request.getParams(), request.getOffset(), request.getLimit());
            }
        }).handle(request);
    }

    /**
     * 添加商品詳情信息圖片
     *
     * @param file    文件
     * @param request 返回参数
     * @return GoodInfo
     * @author Hosmos
     * @date 2021-09-26
     */
    @LogAnnotation
    @PostMapping("/addGoodInfo")
    @ApiOperation(value = "添加商品詳情信息圖片")
    public GoodInfo addGoodInfo(MultipartFile file, HttpServletRequest request) throws IOException {
        return productsShownGoodService.addGoodInfo(file, request);
    }

    /**
     * 设置配送方式树
     *
     * @param deliverWayAll 所有配送方式列表
     * @param array         用JSONArray显示permission
     * @author Hosmos
     * @date 2021-08-27
     */
    private void setDeliverWayTree(List<DeliverWay> deliverWayAll, JSONArray array) {
        for (DeliverWay per : deliverWayAll) {
            String string = JSONObject.toJSONString(per);
            JSONObject deliverWay = (JSONObject) JSONObject.parse(string);
            array.add(deliverWay);
        }
    }
}
