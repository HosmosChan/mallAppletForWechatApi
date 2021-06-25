package com.lettuce.mall.controller.mall;

import com.lettuce.common.utils.Result;
import com.lettuce.common.utils.ResultGenerator;
import com.lettuce.mall.service.MallCarouselService;
import com.lettuce.mall.vo.CarouselsForIndexVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
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
 *
 * @author Hosmos
 * @description 轮播图controller层
 * @date 2021年06月25日
 */
@RestController
@Api(value = "index", tags = "首页接口")
@RequestMapping("/mall/index")
public class MallIndexController {
    @Resource
    private MallCarouselService mallcarouselService;

    /**
     * @param null
     * @return Result<CarouselsForIndexVO>
     * @description 获取轮播图数据
     * @author Hosmos
     * @date 2021/6/25
     */
    @GetMapping("/get_carousel")
    @ApiOperation(value = "获取轮播图数据")
    public Result<CarouselsForIndexVO> getCarousel() {
        List<CarouselsForIndexVO> carousels = mallcarouselService.getCarouselsForIndex();
        return ResultGenerator.genSuccessResult(carousels);
    }
}