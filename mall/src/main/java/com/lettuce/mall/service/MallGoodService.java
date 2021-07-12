package com.lettuce.mall.service;

import com.lettuce.mall.vo.GoodDetailVO;
import com.lettuce.mall.vo.GoodForIndexVO;
import com.lettuce.mall.vo.GoodInfoVO;

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
 * @description 商品业务层
 * @date 2021年06月27日
 */
public interface MallGoodService {
    /**
     * @param number
     * @param appId
     * @return List<GoodForIndexVO>
     * @description 返回特价商品对象(首页调用)
     * @author Hosmos
     * @date 2021-06-27
     */
    List<GoodForIndexVO> getSpecialPriceGoodsForIndex(int number, String appId);

    /**
     * @param number
     * @param appId
     * @return List<GoodForIndexVO>
     * @description 返回折扣商品对象(首页调用)
     * @author Hosmos
     * @date 2021-07-06
     */
    List<GoodForIndexVO> getDiscountGoodsForIndex(int number, String appId);

    /**
     * @param number
     * @param appId
     * @return List<GoodForIndexVO>
     * @description 返回热卖商品对象(首页调用)
     * @author Hosmos
     * @date 2021-07-05
     */
    List<GoodForIndexVO> getHotGoodsForIndex(int number, String appId);

    /**
     * @param number
     * @param appId
     * @return List<GoodForIndexVO>
     * @description 返回最新商品对象(首页调用)
     * @author Hosmos
     * @date 2021-07-05
     */
    List<GoodForIndexVO> getNewGoodsForIndex(int number, String appId);

    /**
     * @param goodId
     * @param appId
     * @return GoodDetailVO
     * @description 返回商品信息对象(商品页调用)
     * @author Hosmos
     * @date 2021-07-05
     */
    GoodDetailVO getGoodDetailByGoodId(Long goodId, String appId);

    /**
     * @param goodId
     * @param appId
     * @return GoodDetailVO
     * @description 返回商品下拉详细信息对象(商品页调用)
     * @author Hosmos
     * @date 2021-07-08
     */
    List<GoodInfoVO> getGoodInfoByGoodId(Long goodId, String appId);
}
