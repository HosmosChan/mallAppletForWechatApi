package com.lettuce.productsShown.controller;

import com.lettuce.common.enums.Constants;
import com.lettuce.common.enums.ServiceResultEnum;
import com.lettuce.common.exception.ProductsShownException;
import com.lettuce.common.utils.Result;
import com.lettuce.common.utils.ResultGenerator;
import com.lettuce.productsShown.service.ProductsShownCarouselService;
import com.lettuce.productsShown.service.ProductsShownGoodService;
import com.lettuce.productsShown.vo.CarouselsForGoodVO;
import com.lettuce.productsShown.vo.GoodDetailVO;
import com.lettuce.productsShown.vo.GoodInfoVO;
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
 * 商品controller层
 * @author Hosmos
 * @date 2021年07月05日
 */
@Api(value = "good", tags = "商品接口")
@RestController
@RequestMapping("/good")
public class ProductsShownGoodController {
    private static final Logger logger = LoggerFactory.getLogger(ProductsShownGoodController.class);
    @Resource
    private ProductsShownCarouselService productsShownCarouselService;
    @Resource
    private ProductsShownGoodService productsShownGoodService;

    /**
     * 获取商品轮播图数据
     * @param goodId 商品 id
     * @param appId app id
     * @return Result<CarouselsForGoodVO>
     * @author Hosmos
     * @date 2021-07-05
     */
    @RequestMapping(value = "/detail/carousels/{goodId}", method = RequestMethod.POST)
    @ApiOperation(value = "获取商品轮播图数据", notes = "传参为商品id")
    public Result<CarouselsForGoodVO> getGoodCarousels(@ApiParam(value = "商品id") @PathVariable("goodId") Long goodId, @ApiParam(value = "APPID") String appId) {
        if (goodId < 1) {
            return ResultGenerator.genFailResult("参数异常");
        }
        List<CarouselsForGoodVO> carousels = productsShownCarouselService.getCarouselsForGood(goodId, appId);
        if (carousels == null) {
            return ResultGenerator.genFailResult("参数异常");
        }
        return ResultGenerator.genSuccessResult(carousels);
    }

    /**
     * 获取商品信息数据
     * @param goodId 商品 id
     * @param appId app id
     * @return Result<GoodDetailVO>
     * @author Hosmos
     * @date 2021-07-05
     */
    @RequestMapping(value = "/detail/{goodId}", method = RequestMethod.POST)
    @ApiOperation(value = "获取商品详情数据", notes = "传参为商品id")
    public Result<GoodDetailVO> getGoodDetail(@ApiParam(value = "商品id") @PathVariable("goodId") Long goodId, @ApiParam(value = "APPID") String appId) {
        if (goodId < 1) {
            return ResultGenerator.genFailResult("参数异常");
        }
        GoodDetailVO goodDetailVO = productsShownGoodService.getGoodDetailByGoodId(goodId, appId);
        switch (goodDetailVO.getSellStatus()) {
            case Constants.SELL_STATUS_EXAMINE: {
                ProductsShownException.fail(ServiceResultEnum.GOODS_PUT_EXAMINE.getResult());
                break;
            }
            case Constants.SELL_STATUS_OFF: {
                ProductsShownException.fail(ServiceResultEnum.GOODS_PUT_OFF.getResult());
                break;
            }
            case Constants.SELL_STATUS_ON: {
                break;
            }
            default:
                return ResultGenerator.genFailResult("参数异常");
        }
        return ResultGenerator.genSuccessResult(goodDetailVO);
    }

    /**
     * 获取商品下拉详细信息数据
     * @param goodId 商品 id
     * @param appId app id
     * @return List<GoodInfoVO>
     * @author Hosmos
     * @date 2021-07-08
     */
    @RequestMapping(value = "/info/{goodId}", method = RequestMethod.POST)
    @ApiOperation(value = "获取下拉详细信息数据", notes = "传参为商品id")
    public Result<List<GoodInfoVO>> getGoodInfo(@ApiParam(value = "商品id") @PathVariable("goodId") Long goodId, @ApiParam(value = "APPID") String appId) {
        if (goodId < 1) {
            return ResultGenerator.genFailResult("参数异常");
        }
        List<GoodInfoVO> goodInfos = productsShownGoodService.getGoodInfoByGoodId(goodId, appId);
        return ResultGenerator.genSuccessResult(goodInfos);
    }
}
