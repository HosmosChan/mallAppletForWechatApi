package com.lettuce.management.dao;

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
 * 日志dao层
 *
 * @author Hosmos
 * @date 2021年07月13日
 */
public interface ManagementSysLogsDao {
    /**
     * 保存日志
     *
     * @param sysLogs 日志信息
     * @author Hosmos
     * @date 2021-08-24
     */
    void save(SysLogs sysLogs);

    /**
     * 删除time之前的日志
     *
     * @param time 时间
     * @return int
     * @author Hosmos
     * @date 2021-08-24
     */
    int deleteLogs(String time);

    /**
     * 获取日志个数
     *
     * @param params 搜索参数
     * @return int
     * @author Hosmos
     * @date 2021-08-24
     */
    int count(Map<String, Object> params);

    /**
     * 获取日志列表
     *
     * @param params 搜索参数
     * @param offset 顺序
     * @param limit  页码
     * @return List<SysLogs>
     * @author Hosmos
     * @date 2021-08-24
     */
    List<SysLogs> list(Map<String, Object> params, Integer offset, Integer limit);
}
