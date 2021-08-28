package com.lettuce.management.service.impl;

import com.lettuce.common.utils.StrUtils;
import com.lettuce.management.dao.ManagementCompanyDao;
import com.lettuce.management.entity.Company;
import com.lettuce.management.service.ManagementCompanyService;
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
 * 公司配置实现层
 *
 * @author Hosmos
 * @date 2021年08月28日
 */
@Service
public class ManagementCompanyServiceImpl implements ManagementCompanyService {
    private static final Logger log = LoggerFactory.getLogger("adminLogger");
    @Autowired
    private ManagementCompanyDao managementCompanyDao;

    @Override
    public List<Company> getCompany() {
        return managementCompanyDao.getCompany();
    }

    @Override
    public int count(Map<String, Object> params) {
        return managementCompanyDao.count(params);
    }

    @Override
    public List<Company> list(Map<String, Object> params, Integer offset, Integer limit) {
        return managementCompanyDao.list(params, offset, limit);
    }

    @Override
    public void save(Company company) {
        Company companyCheck = managementCompanyDao.getByCompany(company.getCompany());
        if (companyCheck != null) {
            throw new IllegalArgumentException("该公司已存在");
        } else {
            company.setCompanyId(StrUtils.createRamdomNo());
            company.setCreateUserId(UserUtil.getCurrentUser().getId());
            managementCompanyDao.save(company);
        }
    }

    @Override
    public void update(Company company) {
        Company companyCheck = managementCompanyDao.getByCompany(company.getCompany());
        if (companyCheck != null) {
            if (!companyCheck.getCompanyId().equals(company.getCompanyId())) {
                throw new IllegalArgumentException("該公司已登记");
            }
        } else {
            company.setGmtUserId(UserUtil.getCurrentUser().getId());
            managementCompanyDao.update(company);
        }
    }

    @Override
    public void delete(Long id) {
        managementCompanyDao.delete(id);
    }

    @Override
    public Company getById(Long id) {
        return managementCompanyDao.getById(id);
    }
}
