package com.lettuce.management.dao;

import com.lettuce.management.dto.AppletDto;
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
 * 小程序配置dao层
 *
 * @author Hosmos
 * @date 2021年08月26日
 */
public interface ManagementAppletDao {
    /**
     * 通过用户id获取app id
     *
     * @param userId user id
     * @return String
     * @author Hosmos
     * @date 2021-08-25
     */
    String getAppIdByUserId(Long userId);

    /**
     * 获取小程序配置个数
     *
     * @param params 搜索参数
     * @return int
     * @author Hosmos
     * @date 2021-08-26
     */
    int count(Map<String, Object> params);

    /**
     * 获取小程序配置列表
     *
     * @param params 搜索参数
     * @param offset 顺序
     * @param limit  页码
     * @return List<AppletDto>
     * @author Hosmos
     * @date 2021-08-26
     */
    List<AppletDto> list(Map<String, Object> params, Integer offset, Integer limit);

    /**
     * 根据app id获取小程序配置信息
     *
     * @param appId app id
     * @return AppletDto
     * @author Hosmos
     * @date 2021-08-26
     */
    AppletDto getByAppId(String appId);

    /**
     * 根据用户 id获取小程序配置信息
     *
     * @param userId 用户 id
     * @return AppletDto
     * @author Hosmos
     * @date 2021-08-26
     */
    AppletDto getByUserId(Long userId);

    /**
     * 保存小程序配置信息
     *
     * @param appletDto applet dto
     * @author Hosmos
     * @date 2021-08-26
     */
    void save(AppletDto appletDto);

    /**
     * 根据id获取小程序配置
     *
     * @param id applet id
     * @return AppletDto
     * @author Hosmos
     * @date 2021-08-26
     */
    AppletDto getById(Long id);

    /**
     * 更新小程序配置
     *
     * @param appletDto applet dto
     * @author Hosmos
     * @date 2021-08-26
     */
    void update(AppletDto appletDto);

    /**
     * 根据id更改小程序配置为失效
     *
     * @param id        applet id
     * @param gmtUserId 修改用户 id
     * @param status    小程序状态
     * @author Hosmos
     * @date 2021-08-26
     */
    void delete(Long id, Long gmtUserId, Byte status);

    /**
     * 获取公司名和Id
     *
     * @return List<Company>
     * @author Hosmos
     * @date 2021-08-28
     */
    List<Company> getCompany();

    /**
     * 获取公司名和Id
     *
     * @param appId app id
     * @return List<Company>
     * @author Hosmos
     * @date 2021-08-28
     */
    List<Company> getCompanyByAppId(String appId);
}
