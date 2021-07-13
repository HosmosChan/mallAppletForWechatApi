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
 *
 * @author Hosmos
 * @description 字典业务层
 * @date 2021年07月13日
 */
public interface ManagementDictService {
    /**
     * @param type
     * @param key
     * @return Dict
     * @description 通过字典type和key获取字典包含value值的整条字典信息
     * @author Hosmos
     * @date 2021-07-13
     */
    Dict getByTypeAndKey(String type, String key);

    /**
     * @param dict
     * @description 保存字典信息
     * @author Hosmos
     * @date 2021-07-13
     */
    void save(Dict dict);

    /**
     * @param tid
     * @return Dict
     * @description 通过字典tid获取字典信息
     * @author Hosmos
     * @date 2021-07-13
     */
    Dict getByTid(Long tid);

    /**
     * @param dict
     * @description 更新字典信息
     * @author Hosmos
     * @date 2021-07-13
     */
    void update(Dict dict);

    /**
     * @param params
     * @return int
     * @description 通过搜索参数获取字典信息列表个数，用于list方法中进行页码计数
     * @author Hosmos
     * @date 2021-07-13
     */
    int count(Map<String, Object> params);

    /**
     * @param params
     * @param offset
     * @param limit
     * @return List<Dict>
     * @description 通过搜索参数获取字典信息列表
     * @author Hosmos
     * @date 2021-07-13
     */
    List<Dict> list(Map<String, Object> params, Integer offset, Integer limit);

    /**
     * @param tid
     * @description 通过字典tid删除字典信息
     * @author Hosmos
     * @date 2021-07-13
     */
    void delete(Long tid);

    /**
     * @param type
     * @return List<Dict>
     * @description 通过字典类型获取字典信息列表
     * @author Hosmos
     * @date 2021-07-13
     */
    List<Dict> listByType(String type);
}
