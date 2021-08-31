package com.lettuce.management.dao;

import com.lettuce.management.dto.GoodBaseDto;
import com.lettuce.management.dto.GoodDto;
import com.lettuce.management.entity.DeliverWay;
import com.lettuce.management.entity.GoodBase;
import com.lettuce.management.entity.GoodDeliverWay;

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
 * 产品展示商品dao层
 *
 * @author Hosmos
 * @date 2021年08月24日
 */
public interface ProductsShownGoodDao {
    /**
     * 获取商品个数
     *
     * @param params 搜索参数
     * @return int
     * @author Hosmos
     * @date 2021-08-24
     */
    int count(Map<String, Object> params);

    /**
     * 获取商品列表
     *
     * @param params 搜索参数
     * @param offset 顺序
     * @param limit  页码
     * @return List<GoodBase>
     * @author Hosmos
     * @date 2021-08-24
     */
    List<GoodBaseDto> list(Map<String, Object> params, Integer offset, Integer limit);

    /**
     * 根据分类Id查询商品id列表
     *
     * @param categoryId 分类 id
     * @return List<Long>
     * @author Hosmos
     * @date 2021-08-25
     */
    List<Long> getGoodIdByCategoryId(Long categoryId);

    /**
     * 根据商品id列表删除商品基础表
     *
     * @param goodIdList 商品 id 列表
     * @author Hosmos
     * @date 2021-08-25
     */
    void deleteGoodBaseByGoodIdList(List<Long> goodIdList);

    /**
     * 根据商品id列表删除商品信息表
     *
     * @param goodIdList 商品 id 列表
     * @author Hosmos
     * @date 2021-08-25
     */
    void deleteGoodInfoByGoodIdList(List<Long> goodIdList);

    /**
     * 根据商品id列表删除商品详情表
     *
     * @param goodIdList 商品 id 列表
     * @author Hosmos
     * @date 2021-08-25
     */
    void deleteGoodDetailByGoodIdList(List<Long> goodIdList);

    /**
     * 根据商品id列表删除商品配送方式表
     *
     * @param goodIdList 商品 id 列表
     * @author Hosmos
     * @date 2021-08-25
     */
    void deleteGoodDeliverWayByGoodIdList(List<Long> goodIdList);

    /**
     * 根据商品id列表删除商品折扣表
     *
     * @param goodIdList 商品 id 列表
     * @author Hosmos
     * @date 2021-08-25
     */
    void deleteGoodDiscountByGoodIdList(List<Long> goodIdList);

    /**
     * 通过商品名获取商品信息
     *
     * @param goodName 商品名
     * @param appId    app id
     * @return GoodBase
     * @author Hosmos
     * @date 2021-08-25
     */
    GoodBase getGoodByName(String goodName, String appId);

    /**
     * 保存商品基础信息
     *
     * @param goodDto 商品 gto
     * @author Hosmos
     * @date 2021-08-27
     */
    void saveBase(GoodDto goodDto);

    /**
     * 保存商品詳細信息
     *
     * @param goodDto 商品 gto
     * @author Hosmos
     * @date 2021-08-28
     */
    void saveDetail(GoodDto goodDto);

    /**
     * 保存滿減折扣信息
     *
     * @param goodDto 商品 gto
     * @author Hosmos
     * @date 2021-08-28
     */
    void saveDiscount(GoodDto goodDto);

    /**
     * 获取所有配送方式
     *
     * @return List<DeliverWay>
     * @author Hosmos
     * @date 2021-08-28
     */
    List<DeliverWay> listAllDeliverWay();

    /**
     * 保存配送方式信息
     *
     * @param goodDeliverWayList 配送方式列表
     * @author Hosmos
     * @date 2021-08-28
     */
    void saveDeliverWay(List<GoodDeliverWay> goodDeliverWayList);

    /**
     * 根據分類id獲取商品
     *
     * @param appId app id
     * @param categoryId 分類 id
     * @return List<GoodBase>
     * @author Hosmos
     * @date 2021-08-28
     */
    List<GoodBase> getGoodByCategoryId(String appId, Long categoryId);
}
