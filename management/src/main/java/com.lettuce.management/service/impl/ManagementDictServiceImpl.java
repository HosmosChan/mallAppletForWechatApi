package com.lettuce.management.service.impl;

import com.lettuce.management.dao.ManagementDictDao;
import com.lettuce.management.entity.Dict;
import com.lettuce.management.service.ManagementDictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
 * 字典实现层
 *
 * @author Hosmos
 * @date 2021年07月13日
 */
@Service
public class ManagementDictServiceImpl implements ManagementDictService {
    @Autowired
    private ManagementDictDao managementDictDao;

    @Override
    public Dict getByTypeAndKey(String type, String key) {
        return managementDictDao.getByTypeAndKey(type, key);
    }

    @Override
    public void save(Dict dict) {
        managementDictDao.save(dict);
    }

    @Override
    public Dict getById(Long id) {
        return managementDictDao.getById(id);
    }

    @Override
    public void update(Dict dict) {
        managementDictDao.update(dict);
    }

    @Override
    public List<Dict> list(Map<String, Object> params, Integer offset, Integer limit) {
        return managementDictDao.list(params, offset, limit);
    }

    @Override
    public int count(Map<String, Object> params) {
        return managementDictDao.count(params);
    }

    @Override
    public void delete(Long id) {
        managementDictDao.delete(id);
    }

    @Override
    public List<Dict> listByType(String type) {
        return managementDictDao.listByType(type);
    }
}
