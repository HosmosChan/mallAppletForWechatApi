package com.lettuce.management.dao;

import com.lettuce.management.entity.Company;

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
 * 公司配置dao层
 *
 * @author Hosmos
 * @date 2021年08月28日
 */
public interface ManagementCompanyDao {
    /**
     * 获取公司名和Id
     *
     * @return List<Company>
     * @author Hosmos
     * @date 2021-08-28
     */
    List<Company> getCompany();

    /**
     * 获取公司配置个数
     *
     * @param params 搜索参数
     * @return int
     * @author Hosmos
     * @date 2021-08-28
     */
    int count(Map<String, Object> params);

    /**
     * 获取公司配置列表
     *
     * @param params 搜索参数
     * @param offset 顺序
     * @param limit  页码
     * @return List<Company>
     * @author Hosmos
     * @date 2021-08-28
     */
    List<Company> list(Map<String, Object> params, Integer offset, Integer limit);

    /**
     * 根据公司名获取公司配置信息
     *
     * @param company 公司名
     * @return Company
     * @author Hosmos
     * @date 2021-08-28
     */
    Company getByCompany(String company);

    /**
     * 保存公司配置信息
     *
     * @param company 公司实体类
     * @author Hosmos
     * @date 2021-08-28
     */
    void save(Company company);

    /**
     * 更新公司配置信息
     *
     * @param company 公司实体类
     * @author Hosmos
     * @date 2021-08-28
     */
    void update(Company company);

    /**
     * 根据id删除公司配置信息
     *
     * @param id id
     * @author Hosmos
     * @date 2021-08-28
     */
    void delete(Long id);

    /**
     * 根据id获取公司配置信息
     *
     * @param id id
     * @author Hosmos
     * @date 2021-08-28
     */
    Company getById(Long id);
}
