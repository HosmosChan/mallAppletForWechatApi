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
 *
 * @author Hosmos
 * @description 商品dao层
 * @date 2021年06月28日
 */
public interface MallGoodDao {
    List<GoodBase> getSpecialPriceGoodBaseInfo(int number, String appId);

    List<GoodBase> getHotGoodBaseInfo(int number, String appId);

    List<GoodBase> getNewGoodBaseInfo(int number, String appId);

    List<GoodBase> getDiscountGoodBaseInfo(int number, String appId);

    GoodBase getGoodBaseInfo(Long goodId, String appId);

    GoodDetail getGoodDetailInfo(Long goodId, String appId);

    List<GoodDeliverWay> getGoodDeliverWay(Long goodId, String appId);

    GoodDiscount getGoodDiscount(Long goodId, String appId);

    List<GoodInfo> getGoodInfoList(Long goodId, String appId);
}
