package com.lettuce.management.dao;

import com.lettuce.management.dto.GoodBaseDto;
import com.lettuce.management.entity.GoodBase;

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

}
