package com.lettuce.management.service;

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
 * 字典业务层
 *
 * @author Hosmos
 * @date 2021年07月13日
 */
public interface ManagementDictService {
    /**
     * 通过字典type和key获取字典包含value值的整条字典信息
     *
     * @param type 字典类型
     * @param key  字典值
     * @return Dict
     * @author Hosmos
     * @date 2021-07-13
     */
    Dict getByTypeAndKey(String type, String key);

    /**
     * 保存字典信息
     *
     * @param dict dict实体类
     * @author Hosmos
     * @date 2021-07-13
     */
    void save(Dict dict);

    /**
     * 通过字典id获取字典信息
     *
     * @param id dict id
     * @return Dict
     * @author Hosmos
     * @date 2021-07-13
     */
    Dict getById(Long id);

    /**
     * 更新字典信息
     *
     * @param dict dict实体类
     * @author Hosmos
     * @date 2021-07-13
     */
    void update(Dict dict);

    /**
     * 字典信息列表个数，list中页码计数
     *
     * @param params 搜索参数
     * @return int
     * @author Hosmos
     * @date 2021-07-13
     */
    int count(Map<String, Object> params);

    /**
     * 通过搜索参数获取字典信息列表
     *
     * @param params 搜索参数
     * @param offset 每页起始序列
     * @param limit  每页显示个数
     * @return List<Dict>
     * @author Hosmos
     * @date 2021-07-13
     */
    List<Dict> list(Map<String, Object> params, Integer offset, Integer limit);

    /**
     * 通过字典id删除字典信息
     *
     * @param id dict id
     * @author Hosmos
     * @date 2021-07-13
     */
    void delete(Long id);

    /**
     * 通过字典类型获取字典信息列表
     *
     * @param type 字典类型
     * @return List<Dict>
     * @author Hosmos
     * @date 2021-07-13
     */
    List<Dict> listByType(String type);
}
