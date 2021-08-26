package com.lettuce.management.service;

import com.lettuce.management.dto.AppletDto;

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
 * 小程序配置业务层
 *
 * @author Hosmos
 * @date 2021年08月26日
 */
public interface ManagementAppletService {
    /**
     * 通过当前用户获取app id
     *
     * @return String
     * @author Hosmos
     * @date 2021-08-25
     */
    String getAppIdByUserId();

    /**
     * 小程序配置信息列表个数，list中页码计数
     *
     * @param params 搜索参数
     * @return int
     * @author Hosmos
     * @date 2021-08-26
     */
    int count(Map<String, Object> params);

    /**
     * 通过搜索参数获取小程序配置信息列表
     *
     * @param params 搜索参数
     * @param offset 每页起始序列
     * @param limit  每页显示个数
     * @return List<AppletDto>
     * @author Hosmos
     * @date 2021-08-26
     */
    List<AppletDto> list(Map<String, Object> params, Integer offset, Integer limit);

    /**
     * 通过app id获取小程序配置信息
     *
     * @param appId app id
     * @return AppletDto
     * @author Hosmos
     * @date 2021-08-26
     */
    AppletDto getByAppId(String appId);

    /**
     * 保存小程序配置信息
     *
     * @param appletDto applet dto
     * @author Hosmos
     * @date 2021-08-26
     */
    void save(AppletDto appletDto);

    /**
     * 通过小程序配置id获取小程序配置信息
     *
     * @param id applet id
     * @return AppletDto
     * @author Hosmos
     * @date 2021-08-26
     */
    AppletDto getById(Long id);

    /**
     * 更新小程序配置信息
     *
     * @param appletDto applet dto
     * @author Hosmos
     * @date 2021-08-26
     */
    void update(AppletDto appletDto);

    /**
     * 根据id更改小程序配置为失效
     *
     * @param id applet id
     * @author Hosmos
     * @date 2021-08-26
     */
    void delete(Long id);
}
