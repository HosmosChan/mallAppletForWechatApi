package com.lettuce.management.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.lettuce.common.utils.table.PageTableHandler;
import com.lettuce.common.utils.table.PageTableRequest;
import com.lettuce.common.utils.table.PageTableResponse;
import com.lettuce.management.annotation.LogAnnotation;
import com.lettuce.management.constants.ManagementUserConstants;
import com.lettuce.management.dto.GoodBaseDto;
import com.lettuce.management.dto.GoodDto;
import com.lettuce.management.dto.GoodInfoListDto;
import com.lettuce.management.dto.LayuiFile;
import com.lettuce.management.entity.*;
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
     * 通过相關參數获取商品基础信息
     *
     * @param goodId   商品 id
     * @param goodName 商品名
     * @param appId    app id
     * @return GoodBaseDto
     * @author Hosmos
     * @date 2021-08-25
     */
    @GetMapping("/getGoodBaseByParam")
    @ApiOperation(value = "通过相關參數获取商品基础信息")
    public GoodBaseDto getGoodBaseByParam(Long goodId, String goodName, String appId) {
        return productsShownGoodService.getGoodBaseByParam(goodId, goodName, appId);
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

    /**
     * 根據分類Id獲取商品
     *
     * @param categoryId 分類 id
     * @return List<GoodBase>
     * @author Hosmos
     * @date 2021-08-27
     */
    @GetMapping("/getGoodByCategoryId")
    @ApiOperation(value = "根據分類Id獲取商品")
    public List<GoodBase> getGoodByCategoryId(Long categoryId) {
        return productsShownGoodService.getGoodByCategoryId(categoryId);
    }

    /**
     * 通过相關參數获取商品信息
     *
     * @param goodId 商品 id
     * @return GoodDto
     * @author Hosmos
     * @date 2021-10-08
     */
    @GetMapping("/getGoodByParam")
    @ApiOperation(value = "通过相關參數获取商品信息")
    public GoodDto getGoodByParam(Long goodId) {
        return productsShownGoodService.getGoodByParam(goodId);
    }

    /**
     * 根据商品id获取配送方式
     *
     * @param goodId 商品id
     * @return List<DeliverWay>
     * @author Hosmos
     * @date 2021-10-10
     */
    @GetMapping(params = "goodId", value = "/getDeliverWayByGoodId")
    @ApiOperation(value = "根据商品id获取配送方式")
    public List<DeliverWay> getDeliverWayByGoodId(Long goodId) {
        return productsShownGoodService.getDeliverWayByGoodId(goodId);
    }

    /**
     * 上传商品封面
     *
     * @param file    文件
     * @param request 请求参数
     * @return LayuiFile
     * @author Hosmos
     * @date 2021-10-11
     */
    @PostMapping("/uploadCover")
    @ApiOperation(value = "上传商品封面")
    @RequiresPermissions("productsShown:good:add")
    public LayuiFile uploadCover(MultipartFile file, HttpServletRequest request) throws IOException {
        GoodInfo fileInfo = productsShownGoodService.uploadCover(file, request);
        LayuiFile layuiFile = new LayuiFile();
        LayuiFile.LayuiFileData data = new LayuiFile.LayuiFileData();
        data.setTitle(file.getOriginalFilename());
        if (fileInfo != null) {
            data.setSrc(request.getParameter("domain") + "/mallAppletForWechatApi/files/goodCover" + fileInfo.getUrl());
            layuiFile.setCode(0);
            layuiFile.setMsg("上传文件成功：" + file.getName());
        } else {
            layuiFile.setCode(1);
            layuiFile.setMsg("上传文件失败：" + file.getName());
        }
        layuiFile.setData(data);
        return layuiFile;
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
     * @return LayuiFile
     * @author Hosmos
     * @date 2021-09-26
     */
    @LogAnnotation
    @PostMapping("/addGoodInfo")
    @ApiOperation(value = "添加商品詳情信息圖片")
    public LayuiFile addGoodInfo(MultipartFile file, HttpServletRequest request) throws IOException {
        GoodInfo fileInfo = productsShownGoodService.addGoodInfo(file, request);
        LayuiFile layuiFile = new LayuiFile();
        LayuiFile.LayuiFileData data = new LayuiFile.LayuiFileData();
        data.setTitle(file.getOriginalFilename());
        if (fileInfo != null) {
            data.setSrc(request.getParameter("domain") + "/mallAppletForWechatApi/files/goodInfo" + fileInfo.getUrl());
            layuiFile.setCode(0);
            layuiFile.setMsg("上传文件成功：" + file.getName());
        } else {
            layuiFile.setCode(1);
            layuiFile.setMsg("上传文件失败：" + file.getName());
        }
        layuiFile.setData(data);
        return layuiFile;
    }

    /**
     * 删除商品詳情信息圖片
     *
     * @param goodId 商品 id
     * @author Hosmos
     * @date 2021-10-18
     */
    @LogAnnotation
    @DeleteMapping("/deleteGoodInfo/{goodId}")
    @ApiOperation(value = "删除商品詳情信息圖片")
    public void deleteGoodInfo(@PathVariable Long goodId) {
        productsShownGoodService.deleteGoodInfo(goodId, ManagementUserConstants.GOOD_INFO);
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
