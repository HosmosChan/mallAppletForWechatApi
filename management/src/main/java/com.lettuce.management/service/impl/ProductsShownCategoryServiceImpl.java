package com.lettuce.management.service.impl;

import com.lettuce.common.utils.FileUtil;
import com.lettuce.common.utils.StrUtils;
import com.lettuce.management.config.YmlConfig;
import com.lettuce.management.constants.ManagementUserConstants;
import com.lettuce.management.dao.ManagementAppletDao;
import com.lettuce.management.dao.ProductsShownCategoryDao;
import com.lettuce.management.dao.ProductsShownGoodDao;
import com.lettuce.management.entity.Category;
import com.lettuce.management.entity.GoodInfo;
import com.lettuce.management.service.ProductsShownCategoryService;
import com.lettuce.management.service.ProductsShownGoodService;
import com.lettuce.management.utils.UserUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
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
    @Value("${files.path}")
    private String filesPath;
    @Autowired
    private ProductsShownCategoryDao productsShownCategoryDao;
    @Autowired
    private ProductsShownGoodDao productsShownGoodDao;
    @Autowired
    private ManagementAppletDao managementAppletDao;
    @Autowired
    private ProductsShownGoodService productsShownGoodService;
    @Autowired
    private YmlConfig ymlConfig;

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
    @Transactional(rollbackFor = Exception.class)
    public void save(MultipartFile file, String appId, String categoryName) throws IOException {
        Category category = new Category();
        category.setAppId(appId);
        category.setCategoryId(StrUtils.createRamdomNo());
        category.setCategoryName(categoryName);
        String fileOriginalName = file.getOriginalFilename();
        Long id = StrUtils.createRamdomNo();
        String md5 = FileUtil.fileMd5(file.getInputStream());
        GoodInfo fileInfo = new GoodInfo();
        fileOriginalName = fileOriginalName.substring(fileOriginalName.lastIndexOf("."));
        String pathname = "/" + category.getCategoryId() + "/" + md5 + fileOriginalName;
        String fullPath = filesPath + ymlConfig.getFile().getCategory() + pathname;
        category.setCategoryImg(fullPath);
        category.setCreateUserId(UserUtil.getCurrentUser().getId());
        productsShownCategoryDao.save(category);
        long size = file.getSize();
        String contentType = file.getContentType();
        fileInfo.setId(id);
        fileInfo.setPictureId(md5);
        fileInfo.setAppId(appId);
        fileInfo.setGoodId(category.getCategoryId());
        fileInfo.setInfoType(ManagementUserConstants.CATEGORY);
        fileInfo.setContentType(contentType);
        fileInfo.setSize(size);
        fileInfo.setPath(fullPath);
        fileInfo.setUrl(pathname);
        fileInfo.setType(contentType.startsWith("image/") ? 1 : 0);
        fileInfo.setCreateUserId(UserUtil.getCurrentUser().getId());
        productsShownGoodDao.addGoodInfo(fileInfo);
        FileUtil.saveFile(file, fullPath);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(MultipartFile file, String appId, String categoryName) throws IOException {
        Category category = new Category();
        category.setAppId(appId);
        category.setCategoryId(StrUtils.createRamdomNo());
        category.setCategoryName(categoryName);
        GoodInfo fileInfo = new GoodInfo();
        String fullPath = null;
        Long currentUser = UserUtil.getCurrentUser().getId();
        if (!file.isEmpty()) {
            String fileOriginalName = file.getOriginalFilename();
            Long id = StrUtils.createRamdomNo();
            String md5 = FileUtil.fileMd5(file.getInputStream());
            fileOriginalName = fileOriginalName.substring(fileOriginalName.lastIndexOf("."));
            String pathname = "/" + category.getCategoryId() + "/" + md5 + fileOriginalName;
            fullPath = filesPath + ymlConfig.getFile().getCategory() + pathname;
            category.setCategoryImg(fullPath);
            long size = file.getSize();
            String contentType = file.getContentType();
            fileInfo.setId(id);
            fileInfo.setPictureId(md5);
            fileInfo.setAppId(appId);
            fileInfo.setGoodId(category.getCategoryId());
            fileInfo.setInfoType(ManagementUserConstants.CATEGORY);
            fileInfo.setContentType(contentType);
            fileInfo.setSize(size);
            fileInfo.setPath(fullPath);
            fileInfo.setUrl(pathname);
            fileInfo.setType(contentType.startsWith("image/") ? 1 : 0);
            fileInfo.setCreateUserId(currentUser);
        }
        category.setGmtUserId(currentUser);
        productsShownCategoryDao.update(category);
        if (!file.isEmpty()) {
            String oldPath = productsShownCategoryDao.getCategoryByName(categoryName, appId).getCategoryImg();
            productsShownGoodService.deleteGoodInfo(category.getCategoryId(), ManagementUserConstants.CATEGORY);
            productsShownGoodDao.addGoodInfo(fileInfo);
            FileUtil.deleteFile(oldPath);
            FileUtil.saveFile(file, fullPath);
        }
    }

    @Override
    public Category getById(Long id) {
        return productsShownCategoryDao.getById(id);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(Long id) {
        Long categoryId = productsShownCategoryDao.getById(id).getCategoryId();
        List<Long> goodIdList = productsShownGoodDao.getGoodIdByCategoryId(categoryId);
        if (goodIdList != null && goodIdList.size() != 0) {
            productsShownGoodDao.deleteGoodDiscountByGoodIdList(goodIdList);
            productsShownGoodDao.deleteGoodDeliverWayByGoodIdList(goodIdList);
            productsShownGoodDao.deleteGoodDetailByGoodIdList(goodIdList);
            productsShownGoodDao.deleteGoodInfoByGoodIdList(goodIdList);
            productsShownGoodDao.deleteGoodBaseByGoodIdList(goodIdList);
        }
        productsShownCategoryDao.delete(id);
        productsShownGoodService.deleteGoodInfo(categoryId, ManagementUserConstants.CATEGORY);
    }

    @Override
    public List<Category> getCategoryByAppId(String appId) {
        return productsShownCategoryDao.getCategoryByAppId(appId);
    }
}
