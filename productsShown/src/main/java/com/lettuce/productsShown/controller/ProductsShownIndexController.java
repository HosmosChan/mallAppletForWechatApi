package com.lettuce.productsShown.controller;

import com.lettuce.common.enums.Constants;
import com.lettuce.common.utils.Result;
import com.lettuce.common.utils.ResultGenerator;
import com.lettuce.productsShown.service.ProductsShownCarouselService;
import com.lettuce.productsShown.service.ProductsShownGoodService;
import com.lettuce.productsShown.vo.CarouselsForIndexVO;
import com.lettuce.productsShown.vo.GoodForIndexVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
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
 *
 * @author Hosmos
 * @description 首页controller层
 * @date 2021年06月25日
 */
@Api(value = "index", tags = "首页接口")
@RestController
@RequestMapping("/productsShown/index")
public class ProductsShownIndexController {
    private static final Logger logger = LoggerFactory.getLogger(ProductsShownIndexController.class);
    @Resource
    private ProductsShownCarouselService productsShownCarouselService;
    @Resource
    private ProductsShownGoodService productsShownGoodService;

    /**
     * @param appId
     * @return Result<CarouselsForIndexVO>
     * @description 获取轮播图数据
     * @author Hosmos
     * @date 2021/6/25
     */
    @RequestMapping(value = "/carousel", method = RequestMethod.POST)
    @ApiOperation(value = "获取轮播图数据")
    public Result<CarouselsForIndexVO> getCarousel(@ApiParam(value = "APPID") String appId) {
        List<CarouselsForIndexVO> carousels = productsShownCarouselService.getCarouselsForIndex(appId);
        return ResultGenerator.genSuccessResult(carousels);
    }

    /**
     * @param appId
     * @return Result<GoodForIndexVO>
     * @description 获取特价商品数据
     * @author Hosmos
     * @date 2021/6/27
     */
    @RequestMapping(value = "/specialPriceGoods", method = RequestMethod.POST)
    @ApiOperation(value = "获取特价商品数据")
    public Result<GoodForIndexVO> getSpecialPriceGoods(@ApiParam(value = "APPID") String appId) {
        List<GoodForIndexVO> specialPriceGoods = productsShownGoodService.getSpecialPriceGoodsForIndex(Constants.INDEX_GOODS_SPECIAL_PRICE_NUMBER, appId);
        return ResultGenerator.genSuccessResult(specialPriceGoods);
    }

    /**
     * @param appId
     * @return Result<GoodForIndexVO>
     * @description 获取折扣商品数据
     * @author Hosmos
     * @date 2021/7/06
     */
    @RequestMapping(value = "/discountGoods", method = RequestMethod.POST)
    @ApiOperation(value = "获取特价商品数据")
    public Result<GoodForIndexVO> getDiscountGoods(@ApiParam(value = "APPID") String appId) {
        List<GoodForIndexVO> discountGoods = productsShownGoodService.getDiscountGoodsForIndex(Constants.INDEX_GOODS_DISCOUNT_NUMBER, appId);
        return ResultGenerator.genSuccessResult(discountGoods);
    }

    /**
     * @param appId
     * @return Result<GoodForIndexVO>
     * @description 获取热卖商品数据
     * @author Hosmos
     * @date 2021-07-05
     */
    @RequestMapping(value = "/hotGoods", method = RequestMethod.POST)
    @ApiOperation(value = "获取热卖商品数据")
    public Result<GoodForIndexVO> getHotGoods(@ApiParam(value = "APPID") String appId) {
        List<GoodForIndexVO> hotGoods = productsShownGoodService.getHotGoodsForIndex(Constants.INDEX_GOODS_HOT_NUMBER, appId);
        return ResultGenerator.genSuccessResult(hotGoods);
    }

    /**
     * @param appId
     * @return Result<GoodForIndexVO>
     * @description 获取最新商品数据
     * @author Hosmos
     * @date 2021-07-05
     */
    @RequestMapping(value = "/getNewGoods", method = RequestMethod.POST)
    @ApiOperation(value = "获取最新商品数据")
    public Result<GoodForIndexVO> getNewGoods(@ApiParam(value = "APPID") String appId) {
        List<GoodForIndexVO> newGoods = productsShownGoodService.getNewGoodsForIndex(Constants.INDEX_GOODS_NEW_NUMBER, appId);
        return ResultGenerator.genSuccessResult(newGoods);
    }
}