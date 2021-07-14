package com.lettuce.management.service;

import com.lettuce.management.entity.SysLogs;

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
 * 日志业务层
 *
 * @author Hosmos
 * @date 2021年07月08日
 */
public interface ManagementSysLogsService {
    /**
     * 通过日志信息保存日志
     *
     * @param sysLogs 日志信息
     * @author Hosmos
     * @date 2021-07-14
     */
    void save(SysLogs sysLogs);

    /**
     * 退出时保存日志
     *
     * @param userId 用户id
     * @param module 退出类型
     * @param flag   保存日志成功与否
     * @param remark 重复操作标志
     * @author Hosmos
     * @date 2021-07-14
     */
    void save(Long userId, String module, Boolean flag, String remark);

    /**
     * 删除{}之前日志{}条
     *
     * @author Hosmos
     * @date 2021-07-14
     */
    void deleteLogs();

    /**
     * 日志信息列表个数，list中页码计数
     *
     * @param params 搜索参数(包含角色昵称(nickName = "?")、开始时间(beginTime = "?")、结束时间(endTime = "?")、状态(flag = ?)以及排列顺序(orderBy = order by ?))
     * @return int
     * @author Hosmos
     * @date 2021-07-14
     */
    int count(Map<String, Object> params);

    /**
     * 通过搜索参数获取日志信息列表
     *
     * @param params 搜索参数(包含角色昵称(nickName = "?")、开始时间(beginTime = "?")、结束时间(endTime = "?")、状态(flag = ?)以及排列顺序(orderBy = order by ?))
     * @param offset 指定排序顺序(asc或desc)
     * @param limit  每页显示个数
     * @return List<SysLogs>
     * @author Hosmos
     * @date 2021-07-14
     */
    List<SysLogs> list(Map<String, Object> params, Integer offset, Integer limit);
}
