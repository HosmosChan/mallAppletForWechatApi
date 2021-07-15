package com.lettuce.mall.controller;

import com.lettuce.common.enums.Constants;
import com.lettuce.common.exception.MallException;
import com.lettuce.common.utils.PageQueryUtil;
import com.lettuce.common.utils.PageResult;
import com.lettuce.common.utils.Result;
import com.lettuce.common.utils.ResultGenerator;
import com.lettuce.mall.service.MallSearchService;
import com.lettuce.mall.vo.SearchVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
 * @description 搜索controller层
 * @date 2021年07月06日
 */
@Api(value = "search", tags = "搜索接口")
@RestController
@RequestMapping("/search")
public class MallSearchController {
    private static final Logger logger = LoggerFactory.getLogger(MallSearchController.class);
    @Resource
    private MallSearchService mallSearchService;

    /**
     * @param keyWord
     * @param categoryId
     * @param orderBy
     * @param pageNumber
     * @param appId
     * @return Result<PageResult < List < MallSearchVO>>>
     * @description 商品搜索(根据关键字和分类id进行搜索)
     * @author Hosmos
     * @date 2021-07-06
     */
    @RequestMapping(value = "/list", method = RequestMethod.POST)
    @ApiOperation(value = "商品搜索")
    public Result<PageResult<List<SearchVO>>> search(@RequestParam(required = false) @ApiParam(value = "搜索关键字") String keyWord, @RequestParam(required = false) @ApiParam(value = "分类id") Long categoryId, @RequestParam(required = false) @ApiParam(value = "orderBy") String orderBy, @RequestParam(required = false) @ApiParam(value = "页码") Integer pageNumber, @ApiParam(value = "APPID") String appId) {
        Map params = new HashMap(8);
        //两个搜索参数都为空，直接返回异常
        if (categoryId == null && StringUtils.isEmpty(keyWord)) {
            MallException.fail("非法的搜索参数");
        }
        if (pageNumber == null || pageNumber < 1) {
            pageNumber = 1;
        }
        params.put("categoryId", categoryId);
        params.put("page", pageNumber);
        params.put("limit", Constants.GOODS_SEARCH_PAGE_LIMIT);
        params.put("appId", appId);
        //对keyword做过滤 去掉空格
        if (!StringUtils.isEmpty(keyWord)) {
            params.put("keyWord", keyWord);
        }
        if (!StringUtils.isEmpty(orderBy)) {
            params.put("orderBy", orderBy);
        }
        //搜索上架状态下的商品
        params.put("sellStatus", Constants.SELL_STATUS_ON);
        //封装商品数据
        PageQueryUtil pageUtil = new PageQueryUtil(params);
        return ResultGenerator.genSuccessResult(mallSearchService.searchGoods(pageUtil));
    }
}
