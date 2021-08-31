package com.lettuce.management.service;

import com.lettuce.management.dto.GoodBaseDto;
import com.lettuce.management.dto.GoodDto;
import com.lettuce.management.entity.DeliverWay;
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
 * 产品展示商品业务层
 *
 * @author Hosmos
 * @date 2021年08月24日
 */
public interface ProductsShownGoodService {
    /**
     * 商品基础信息列表个数，list中页码计数
     *
     * @param params 搜索参数
     * @return int
     * @author Hosmos
     * @date 2021-08-24
     */
    int count(Map<String, Object> params);

    /**
     * 通过搜索参数获取商品基础信息列表
     *
     * @param params 搜索参数
     * @param offset 每页起始序列
     * @param limit  每页显示个数
     * @return List<GoodBase>
     * @author Hosmos
     * @date 2021-08-24
     */
    List<GoodBaseDto> list(Map<String, Object> params, Integer offset, Integer limit);

    /**
     * 保存商品信息
     *
     * @param goodDto 商品实体类
     * @author Hosmos
     * @date 2021-08-27
     */
    void save(GoodDto goodDto);

    /**
     * 获取所有配送方式列表
     *
     * @return List<DeliverWay>
     * @author Hosmos
     * @date 2021-08-28
     */
    List<DeliverWay> listAllDeliverWay();

    /**
     * 根據分類Id獲取商品
     *
     * @param categoryId 分類 Id
     * @return List<GoodBase>
     * @author Hosmos
     * @date 2021-08-30
     */
    List<GoodBase> getGoodByCategoryId(Long categoryId);
}
