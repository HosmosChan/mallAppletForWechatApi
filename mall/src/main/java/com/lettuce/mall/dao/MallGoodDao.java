package com.lettuce.mall.dao;

import com.lettuce.mall.entity.*;

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
 * 商品dao层
 *
 * @author Hosmos
 * @date 2021年06月28日
 */
public interface MallGoodDao {
    /**
     * 特价商品基础信息
     *
     * @param number 商品显示数量
     * @param appId  app id
     * @return List<GoodBase>
     * @author Hosmos
     * @date 2021-08-23
     */
    List<GoodBase> getSpecialPriceGoodBaseInfo(int number, String appId);

    /**
     * 热卖商品基础信息
     *
     * @param number 商品显示数量
     * @param appId  app id
     * @return List<GoodBase>
     * @author Hosmos
     * @date 2021-08-23
     */
    List<GoodBase> getHotGoodBaseInfo(int number, String appId);

    /**
     * 最新商品基础信息
     *
     * @param number 商品显示数量
     * @param appId  app id
     * @return List<GoodBase>
     * @author Hosmos
     * @date 2021-08-23
     */
    List<GoodBase> getNewGoodBaseInfo(int number, String appId);

    /**
     * 折扣商品基础信息
     *
     * @param number 商品显示数量
     * @param appId  app id
     * @return List<GoodBase>
     * @author Hosmos
     * @date 2021-08-23
     */
    List<GoodBase> getDiscountGoodBaseInfo(int number, String appId);

    /**
     * 商品基础信息
     *
     * @param goodId 商品 id
     * @param appId  app id
     * @return GoodBase
     * @author Hosmos
     * @date 2021-08-23
     */
    GoodBase getGoodBaseInfo(Long goodId, String appId);

    /**
     * 商品详细信息
     *
     * @param goodId 商品 id
     * @param appId  app id
     * @return GoodDetail
     * @author Hosmos
     * @date 2021-08-23
     */
    GoodDetail getGoodDetailInfo(Long goodId, String appId);

    /**
     * 商品配送方式
     *
     * @param goodId 商品 id
     * @param appId  app id
     * @return List<GoodDeliverWay>
     * @author Hosmos
     * @date 2021-08-23
     */
    List<GoodDeliverWay> getGoodDeliverWay(Long goodId, String appId);

    /**
     * 商品折扣
     *
     * @param goodId 商品 id
     * @param appId  app id
     * @return GoodDiscount
     * @author Hosmos
     * @date 2021-08-23
     */
    GoodDiscount getGoodDiscount(Long goodId, String appId);

    /**
     * 商品详情列表
     *
     * @param goodId 商品 id
     * @param appId  app id
     * @return List<GoodInfo>
     * @author Hosmos
     * @date 2021-08-23
     */
    List<GoodInfo> getGoodInfoList(Long goodId, String appId);
}
