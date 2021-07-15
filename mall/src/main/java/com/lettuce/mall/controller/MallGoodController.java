package com.lettuce.mall.controller;

import com.lettuce.common.enums.Constants;
import com.lettuce.common.enums.ServiceResultEnum;
import com.lettuce.common.exception.MallException;
import com.lettuce.common.utils.Result;
import com.lettuce.common.utils.ResultGenerator;
import com.lettuce.mall.service.MallCarouselService;
import com.lettuce.mall.service.MallGoodService;
import com.lettuce.mall.vo.CarouselsForGoodVO;
import com.lettuce.mall.vo.GoodDetailVO;
import com.lettuce.mall.vo.GoodInfoVO;
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
 * @description 商品controller层
 * @date 2021年07月05日
 */
@Api(value = "good", tags = "商品接口")
@RestController
@RequestMapping("/good")
public class MallGoodController {
    private static final Logger logger = LoggerFactory.getLogger(MallGoodController.class);
    @Resource
    private MallCarouselService mallCarouselService;
    @Resource
    private MallGoodService mallGoodService;

    /**
     * @param goodId
     * @param appId
     * @return Result<CarouselsForGoodVO>
     * @description 获取商品轮播图数据
     * @author Hosmos
     * @date 2021-07-05
     */
    @RequestMapping(value = "/detail/carousels/{goodId}", method = RequestMethod.POST)
    @ApiOperation(value = "获取商品轮播图数据", notes = "传参为商品id")
    public Result<CarouselsForGoodVO> getGoodCarousels(@ApiParam(value = "商品id") @PathVariable("goodId") Long goodId, @ApiParam(value = "APPID") String appId) {
        if (goodId < 1) {
            return ResultGenerator.genFailResult("参数异常");
        }
        List<CarouselsForGoodVO> carousels = mallCarouselService.getCarouselsForGood(goodId, appId);
        if (carousels == null) {
            return ResultGenerator.genFailResult("参数异常");
        }
        return ResultGenerator.genSuccessResult(carousels);
    }

    /**
     * @param goodId
     * @param appId
     * @return Result<GoodDetailVO>
     * @description 获取商品信息数据
     * @author Hosmos
     * @date 2021-07-05
     */
    @RequestMapping(value = "/detail/{goodId}", method = RequestMethod.POST)
    @ApiOperation(value = "获取商品详情数据", notes = "传参为商品id")
    public Result<GoodDetailVO> getGoodDetail(@ApiParam(value = "商品id") @PathVariable("goodId") Long goodId, @ApiParam(value = "APPID") String appId) {
        if (goodId < 1) {
            return ResultGenerator.genFailResult("参数异常");
        }
        GoodDetailVO goodDetailVO = mallGoodService.getGoodDetailByGoodId(goodId, appId);
        switch (goodDetailVO.getSellStatus()) {
            case Constants.SELL_STATUS_EXAMINE: {
                MallException.fail(ServiceResultEnum.GOODS_PUT_EXAMINE.getResult());
                break;
            }
            case Constants.SELL_STATUS_OFF: {
                MallException.fail(ServiceResultEnum.GOODS_PUT_OFF.getResult());
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
     * @param goodId
     * @param appId
     * @return List<GoodInfoVO>
     * @description 获取商品下拉详细信息数据
     * @author Hosmos
     * @date 2021-07-08
     */
    @RequestMapping(value = "/info/{goodId}", method = RequestMethod.POST)
    @ApiOperation(value = "获取下拉详细信息数据", notes = "传参为商品id")
    public Result<List<GoodInfoVO>> getGoodInfo(@ApiParam(value = "商品id") @PathVariable("goodId") Long goodId, @ApiParam(value = "APPID") String appId) {
        if (goodId < 1) {
            return ResultGenerator.genFailResult("参数异常");
        }
        List<GoodInfoVO> goodInfos = mallGoodService.getGoodInfoByGoodId(goodId, appId);
        return ResultGenerator.genSuccessResult(goodInfos);
    }
}
