package com.lettuce.mall.service;

import com.lettuce.mall.entity.GoodBase;
import com.lettuce.mall.entity.GoodDetail;
import com.lettuce.mall.vo.GoodDetailVO;
import com.lettuce.mall.vo.GoodForIndexVO;

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
 * @description 特价商品业务层
 * @date 2021年06月27日
 */
public interface MallGoodService {
    /**
     * @param null
     * @return List<GoodForIndexVO>
     * @description 返回特价商品对象(首页调用)
     * @author Hosmos
     * @date 2021-06-27
     */
    List<GoodForIndexVO> getSpecialPriceGoodsForIndex(int number);

    /**
     * @param null
     * @return List<GoodForIndexVO>
     * @description 返回热卖商品对象(首页调用)
     * @author Hosmos
     * @date 2021-07-05
     */
    List<GoodForIndexVO> getHotGoodsForIndex(int number);

    /**
     * @param null
     * @return List<GoodForIndexVO>
     * @description 返回最新商品对象(首页调用)
     * @author Hosmos
     * @date 2021-07-05
     */
    List<GoodForIndexVO> getNewGoodsForIndex(int number);

    /**
     * @param goodId
     * @return GoodDetailVO
     * @description 返回商品信息对象(商品页调用)
     * @author Hosmos
     * @date 2021-07-05
     */
    GoodDetailVO getGoodDetailByGoodId(Long goodId);

    List<GoodForIndexVO> getDiscountGoodsForIndex(int indexGoodsDiscountNumber);
}
