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
    List<GoodBase> getSpecialPriceGoodBaseInfo(int number);

    List<GoodBase> getHotGoodBaseInfo(int number);

    List<GoodBase> getNewGoodBaseInfo(int number);

    List<GoodBase> getDiscountGoodBaseInfo(int number);

    GoodBase getGoodBaseInfo(Long goodId);

    GoodDetail getGoodDetailInfo(Long goodId);

    List<GoodDeliverWay> getGoodDeliverWay(Long goodId);

    GoodDiscount getGoodDiscount(Long goodId);

    List<GoodInfo> getGoodInfoList(Long goodId);
}
