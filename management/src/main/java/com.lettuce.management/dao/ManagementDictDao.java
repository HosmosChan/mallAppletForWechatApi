package com.lettuce.management.dao;

import com.lettuce.management.entity.Dict;

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
 * 字典dao层
 *
 * @author Hosmos
 * @date 2021年07月13日
 */
public interface ManagementDictDao {
    /**
     * 根据类型和key获取字典
     *
     * @param type 类型
     * @param key  key
     * @return Dict
     * @author Hosmos
     * @date 2021-08-24
     */
    Dict getByTypeAndKey(String type, String key);

    /**
     * 保存字典
     *
     * @param dict 字典信息
     * @author Hosmos
     * @date 2021-08-24
     */
    void save(Dict dict);

    /**
     * 根据id获取字典
     *
     * @param id 字典 id
     * @return Dict
     * @author Hosmos
     * @date 2021-08-24
     */
    Dict getById(Long id);

    /**
     * 更新字典
     *
     * @param dict 字典信息
     * @author Hosmos
     * @date 2021-08-24
     */
    void update(Dict dict);

    /**
     * 获取字典列表
     *
     * @param params 搜索参数
     * @param offset 顺序
     * @param limit  页码
     * @return List<Dict>
     * @author Hosmos
     * @date 2021-08-24
     */
    List<Dict> list(Map<String, Object> params, Integer offset, Integer limit);

    /**
     * 获取字典个数
     *
     * @param params 搜索参数
     * @return int
     * @author Hosmos
     * @date 2021-08-24
     */
    int count(Map<String, Object> params);

    /**
     * 删除字典
     *
     * @param id 字典 id
     * @author Hosmos
     * @date 2021-08-24
     */
    void delete(Long id);

    /**
     * 根据类型获取字典列表
     *
     * @param type 类型
     * @return List<Dict>
     * @author Hosmos
     * @date 2021-08-24
     */
    List<Dict> listByType(String type);
}
