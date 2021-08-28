package com.lettuce.management.service.impl;

import com.lettuce.management.dao.ManagementAppletDao;
import com.lettuce.management.dao.ManagementUserDao;
import com.lettuce.management.dto.AppletDto;
import com.lettuce.management.entity.Company;
import com.lettuce.management.service.ManagementAppletService;
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
 * 小程序配置实现层
 *
 * @author Hosmos
 * @date 2021年08月26日
 */
@Service
public class ManagementAppletServiceImpl implements ManagementAppletService {
    private static final Logger log = LoggerFactory.getLogger("adminLogger");
    @Autowired
    private ManagementAppletDao managementAppletDao;
    @Autowired
    private ManagementUserDao managementUserDao;

    @Override
    public String getAppIdByUserId() {
        Long userId = UserUtil.getCurrentUser().getId();
        return managementAppletDao.getAppIdByUserId(userId);
    }

    @Override
    public int count(Map<String, Object> params) {
        return managementAppletDao.count(params);
    }

    @Override
    public List<AppletDto> list(Map<String, Object> params, Integer offset, Integer limit) {
        return managementAppletDao.list(params, offset, limit);
    }

    @Override
    public AppletDto getByAppId(String appId) {
        return managementAppletDao.getByAppId(appId);
    }

    @Override
    public void save(AppletDto appletDto) {
        Long userId = managementUserDao.getIdByUsername(appletDto.getUsername());
        if (userId != null) {
            AppletDto d = managementAppletDao.getByUserId(userId);
            if (d != null) {
                throw new IllegalArgumentException("該用戶已註冊小程序");
            } else {
                appletDto.setUserId(userId);
                appletDto.setCreateUserId(UserUtil.getCurrentUser().getId());
                managementAppletDao.save(appletDto);
            }
        } else {
            throw new IllegalArgumentException("用戶名不存在");
        }
    }

    @Override
    public AppletDto getById(Long id) {
        return managementAppletDao.getById(id);
    }

    @Override
    public void update(AppletDto appletDto) {
        Long userId = managementUserDao.getIdByUsername(appletDto.getUsername());
        if (userId != null) {
            AppletDto d = managementAppletDao.getByUserId(userId);
            if (d != null && !d.getAppId().equals(appletDto.getAppId())) {
                throw new IllegalArgumentException("該用戶已註冊小程序");
            } else {
                appletDto.setUserId(userId);
                appletDto.setGmtUserId(UserUtil.getCurrentUser().getId());
                managementAppletDao.update(appletDto);
            }
        } else {
            throw new IllegalArgumentException("用戶名不存在");
        }
    }

    @Override
    public void delete(Long id) {
        Long gmtUserId = UserUtil.getCurrentUser().getId();
        Byte status = 2;
        managementAppletDao.delete(id, gmtUserId, status);
    }

    @Override
    public List<Company> getCompanyByAppId(String appId) {
        return managementAppletDao.getCompanyByAppId(appId);
    }
}
