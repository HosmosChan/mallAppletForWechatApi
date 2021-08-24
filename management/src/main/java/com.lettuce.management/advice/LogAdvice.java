package com.lettuce.management.advice;

import com.lettuce.management.annotation.LogAnnotation;
import com.lettuce.management.dao.ManagementUserDao;
import com.lettuce.management.entity.SysLogs;
import com.lettuce.management.service.ManagementSysLogsService;
import com.lettuce.management.utils.UserUtil;
import io.swagger.annotations.ApiOperation;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

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
 * 统一日志处理
 *
 * @author Hosmos
 * @date 2021年07月08日
 */
@Aspect
@Component
public class LogAdvice {
    @Autowired
    private ManagementSysLogsService managementSysLogsService;
    @Autowired
    private ManagementUserDao managementUserDao;

    @Around(value = "@annotation(com.lettuce.management.annotation.LogAnnotation)")
    public Object logSave(ProceedingJoinPoint joinPoint) throws Throwable {
        SysLogs sysLogs = new SysLogs();
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        String module = null;
        LogAnnotation logAnnotation = methodSignature.getMethod().getDeclaredAnnotation(LogAnnotation.class);
        module = logAnnotation.module();
        if (StringUtils.isEmpty(module)) {
            ApiOperation apiOperation = methodSignature.getMethod().getDeclaredAnnotation(ApiOperation.class);
            if (apiOperation != null) {
                module = apiOperation.value();
            }
        }
        if (StringUtils.isEmpty(module)) {
            throw new RuntimeException("没有指定日志module");
        }
        sysLogs.setModule(module);
        try {
            Object object = joinPoint.proceed();
            sysLogs.setFlag(true);
            managementSysLogsService.save(sysLogs);
            if (module.contains("登陆")) {
                managementUserDao.lastLogin(UserUtil.getCurrentUser().getId());
            }
            return object;
        } catch (Exception e) {
            sysLogs.setFlag(false);
            sysLogs.setRemark(e.getMessage());
            managementSysLogsService.save(sysLogs);
            throw e;
        }
    }
}