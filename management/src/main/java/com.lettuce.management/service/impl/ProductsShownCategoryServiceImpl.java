package com.lettuce.management.service.impl;

import com.lettuce.management.dao.ManagementUserDao;
import com.lettuce.management.dao.ProductsShownCategoryDao;
import com.lettuce.management.entity.Category;
import com.lettuce.management.service.ProductsShownCategoryService;
import com.lettuce.management.utils.UserUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
 * 产品展示分类实现层
 *
 * @author Hosmos
 * @date 2021年08月24日
 */
@Service
public class ProductsShownCategoryServiceImpl implements ProductsShownCategoryService {
    private static final Logger log = LoggerFactory.getLogger("adminLogger");
    @Autowired
    private ProductsShownCategoryDao productsShownCategoryDao;
    @Autowired
    private ManagementUserDao managementUserDao;

    @Override
    public int count(Map<String, Object> params) {
        String appId = managementUserDao.getAppIdByUserId(UserUtil.getCurrentUser().getId());
        params.put("appId", appId);
        return productsShownCategoryDao.count(params);
    }

    @Override
    public List<Category> list(Map<String, Object> params, Integer offset, Integer limit) {
        String appId = managementUserDao.getAppIdByUserId(UserUtil.getCurrentUser().getId());
        params.put("appId", appId);
        return productsShownCategoryDao.list(params, offset, limit);
    }
}
