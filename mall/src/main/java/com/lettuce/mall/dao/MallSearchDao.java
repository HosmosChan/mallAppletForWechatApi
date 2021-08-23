package com.lettuce.mall.dao;

import com.lettuce.common.utils.PageQueryUtil;
import com.lettuce.mall.entity.GoodBase;

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
 * 商品搜索dao层
 *
 * @author Hosmos
 * @date 2021年07月06日
 */
public interface MallSearchDao {
    /**
     * 商品搜索列表信息
     *
     * @param pageUtil 页码信息
     * @return List<GoodBase>
     * @author Hosmos
     * @date 2021-08-23
     */
    List<GoodBase> getGoodsListBySearch(PageQueryUtil pageUtil);

    /**
     * 商品搜索列表个数
     *
     * @param pageUtil 页码信息
     * @return int
     * @author Hosmos
     * @date 2021-08-23
     */
    int getSearchNumber(PageQueryUtil pageUtil);
}
