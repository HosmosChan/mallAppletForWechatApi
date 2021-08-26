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

    /**
     * 根据分类名获取分类
     *
     * @param categoryName 分类名
     * @param appId        app id
     * @return Category
     * @author Hosmos
     * @date 2021-08-25
     */
    Category getCategoryByName(String categoryName, String appId);

    /**
     * 保存分类
     *
     * @param category 分类信息
     * @author Hosmos
     * @date 2021-08-25
     */
    void save(Category category);

    /**
     * 更新分类
     *
     * @param category 分类信息
     * @author Hosmos
     * @date 2021-08-25
     */
    void update(Category category);

    /**
     * 根据id获取分类
     *
     * @param id 分类 id
     * @return Category
     * @author Hosmos
     * @date 2021-08-25
     */
    Category getById(Long id);

    /**
     * 删除分类
     *
     * @param id 分类 id
     * @author Hosmos
     * @date 2021-08-25
     */
    void delete(Long id);
}
