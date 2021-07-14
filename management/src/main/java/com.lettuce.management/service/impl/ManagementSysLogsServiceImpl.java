package com.lettuce.management.service.impl;

import com.lettuce.management.dao.ManagementSysLogsDao;
import com.lettuce.management.entity.SysLogs;
import com.lettuce.management.entity.User;
import com.lettuce.management.service.ManagementSysLogsService;
import com.lettuce.management.utils.UserUtil;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.Date;
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
 * 日志实现层
 *
 * @author Hosmos
 * @date 2021年07月13日
 */
@Service
public class ManagementSysLogsServiceImpl implements ManagementSysLogsService {
    private static final Logger log = LoggerFactory.getLogger("adminLogger");
    @Autowired(required = false)
    private ManagementSysLogsDao managementSysLogsDao;

    @Override
    public void save(SysLogs sysLogs) {
        User user = UserUtil.getCurrentUser();
        if (user == null || user.getTid() == null) {
            return;
        }
        sysLogs.setUser(user);
        managementSysLogsDao.save(sysLogs);
    }

    @Async
    @Override
    public void save(Long userId, String module, Boolean flag, String remark) {
        SysLogs sysLogs = new SysLogs();
        sysLogs.setFlag(flag);
        sysLogs.setModule(module);
        sysLogs.setRemark(remark);
        User user = new User();
        user.setTid(userId);
        sysLogs.setUser(user);
        managementSysLogsDao.save(sysLogs);
    }

    @Override
    public void deleteLogs() {
        Date date = DateUtils.addMonths(new Date(), -3);
        String time = DateFormatUtils.format(date, DateFormatUtils.ISO_8601_EXTENDED_DATE_FORMAT.getPattern());
        int n = managementSysLogsDao.deleteLogs(time);
        log.info("删除{}之前日志{}条", time, n);
    }

    @Override
    public int count(Map<String, Object> params) {
        return managementSysLogsDao.count(params);
    }

    @Override
    public List<SysLogs> list(Map<String, Object> params, Integer offset, Integer limit) {
        return managementSysLogsDao.list(params, offset, limit);
    }
}
