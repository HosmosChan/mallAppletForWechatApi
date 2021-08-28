package com.lettuce.management.service;

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
 * 公司配置业务层
 *
 * @author Hosmos
 * @date 2021年08月28日
 */
public interface ManagementCompanyService {
    /**
     * 获取公司名和Id
     *
     * @return List<Company>
     * @author Hosmos
     * @date 2021-08-28
     */
    List<Company> getCompany();

    /**
     * 公司配置信息列表个数，list中页码计数
     *
     * @param params 搜索参数
     * @return int
     * @author Hosmos
     * @date 2021-08-28
     */
    int count(Map<String, Object> params);

    /**
     * 通过搜索参数获取公司配置信息列表
     *
     * @param params 搜索参数
     * @param offset 每页起始序列
     * @param limit  每页显示个数
     * @return List<Company>
     * @author Hosmos
     * @date 2021-08-28
     */
    List<Company> list(Map<String, Object> params, Integer offset, Integer limit);

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
     * 根据公司id删除公司配置信息
     *
     * @param id 公司 id
     * @author Hosmos
     * @date 2021-08-28
     */
    void delete(Long id);

    /**
     * 通过公司id获取公司配置信息
     *
     * @param id id
     * @return Company
     * @author Hosmos
     * @date 2021-08-28
     */
    Company getById(Long id);
}
