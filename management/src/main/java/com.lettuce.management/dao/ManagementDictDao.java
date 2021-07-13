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
 *
 * @author Hosmos
 * @description 字典dao层
 * @date 2021年07月13日
 */
public interface ManagementDictDao {
    Dict getByTypeAndKey(String type, String key);

    void save(Dict dict);

    Dict getByTid(Long tid);

    void update(Dict dict);

    List<Dict> list(Map<String, Object> params, Integer offset, Integer limit);

    int count(Map<String, Object> params);

    void delete(Long tid);

    List<Dict> listByType(String type);
}
