package com.lettuce.management.dao;

import com.lettuce.management.entity.Category;

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
 * 产品展示分类dao层
 *
 * @author Hosmos
 * @date 2021年08月24日
 */
public interface ProductsShownCategoryDao {
    /**
     * 获取所有分类列表
     *
     * @return List<Category>
     * @author Hosmos
     * @date 2021-08-24
     */
    List<Category> listAll();

    /**
     * 获取分类个数
     *
     * @param params 搜索参数
     * @return int
     * @author Hosmos
     * @date 2021-08-24
     */
    int count(Map<String, Object> params);

    /**
     * 获取分类列表
     *
     * @param params 搜索参数
     * @param offset 顺序
     * @param limit  页码
     * @return List<Category>
     * @author Hosmos
     * @date 2021-08-24
     */
    List<Category> list(Map<String, Object> params, Integer offset, Integer limit);
}