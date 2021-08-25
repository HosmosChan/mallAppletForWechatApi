package com.lettuce.management.service;

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
 * 产品展示分类业务层
 *
 * @author Hosmos
 * @date 2021年08月24日
 */
public interface ProductsShownCategoryService {
    /**
     * 分类信息列表个数，list中页码计数
     *
     * @param params 搜索参数
     * @return int
     * @author Hosmos
     * @date 2021-08-24
     */
    int count(Map<String, Object> params);

    /**
     * 通过搜索参数获取分类信息列表
     *
     * @param params 搜索参数
     * @param offset 每页起始序列
     * @param limit  每页显示个数
     * @return List<Category>
     * @author Hosmos
     * @date 2021-08-24
     */
    List<Category> list(Map<String, Object> params, Integer offset, Integer limit);

    /**
     * 通过分类名获取分类信息
     *
     * @param categoryName 分类名
     * @param appId app id
     * @return Category
     * @author Hosmos
     * @date 2021-08-25
     */
    Category getCategoryByName(String categoryName, String appId);

    /**
     * 保存分类信息
     *
     * @param category 分类实体类
     * @author Hosmos
     * @date 2021-08-25
     */
    void save(Category category);

    /**
     * 更新分类信息
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
