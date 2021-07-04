package com.lettuce.mall.service;

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
    List<GoodForIndexVO> getDiscountGoodsForIndex(int number);

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
}
