package com.lettuce.management.service.impl;

import com.lettuce.common.utils.StrUtils;
import com.lettuce.management.dao.ManagementAppletDao;
import com.lettuce.management.dao.ProductsShownCategoryDao;
import com.lettuce.management.dao.ProductsShownGoodDao;
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
    private ProductsShownGoodDao productsShownGoodDao;
    @Autowired
    private ManagementAppletDao managementAppletDao;

    @Override
    public int count(Map<String, Object> params) {
        String appId = managementAppletDao.getAppIdByUserId(UserUtil.getCurrentUser().getId());
        params.put("appId", appId);
        return productsShownCategoryDao.count(params);
    }

    @Override
    public List<Category> list(Map<String, Object> params, Integer offset, Integer limit) {
        String appId = managementAppletDao.getAppIdByUserId(UserUtil.getCurrentUser().getId());
        params.put("appId", appId);
        return productsShownCategoryDao.list(params, offset, limit);
    }

    @Override
    public Category getCategoryByName(String categoryName, String appId) {
        return productsShownCategoryDao.getCategoryByName(categoryName, appId);
    }

    @Override
    public void save(Category category) {
        category.setCategoryId(StrUtils.createRamdomNo());
        category.setCreateUserId(UserUtil.getCurrentUser().getId());
        productsShownCategoryDao.save(category);
    }

    @Override
    public void update(Category category) {
        category.setGmtUserId(UserUtil.getCurrentUser().getId());
        productsShownCategoryDao.update(category);
    }

    @Override
    public Category getById(Long id) {
        return productsShownCategoryDao.getById(id);
    }

    @Override
    public void delete(Long id) {
        Long CategoryId = productsShownCategoryDao.getById(id).getCategoryId();
        List<Long> goodIdList = productsShownGoodDao.getGoodIdByCategoryId(CategoryId);
        if (goodIdList != null && goodIdList.size() != 0) {
            productsShownGoodDao.deleteGoodDiscountByGoodIdList(goodIdList);
            productsShownGoodDao.deleteGoodDeliverWayByGoodIdList(goodIdList);
            productsShownGoodDao.deleteGoodDetailByGoodIdList(goodIdList);
            productsShownGoodDao.deleteGoodInfoByGoodIdList(goodIdList);
            productsShownGoodDao.deleteGoodBaseByGoodIdList(goodIdList);
        }
        productsShownCategoryDao.delete(id);
    }
}
