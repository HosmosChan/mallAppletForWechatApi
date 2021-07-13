package com.lettuce.management.filter;

import com.alibaba.fastjson.JSONObject;
import com.lettuce.management.dto.ResponseInfo;
import com.lettuce.management.entity.User;
import com.lettuce.management.service.ManagementSysLogService;
import com.lettuce.management.service.ManagementTokenManager;
import com.lettuce.management.utils.SpringUtil;
import com.lettuce.management.utils.UserUtil;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.web.util.WebUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

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
 *
 * @author Hosmos
 * @description 退出方式: web退出和restful方式退出, 后者会删除缓存的token
 * @date 2021年07月08日
 */
public class LogoutFilter extends org.apache.shiro.web.filter.authc.LogoutFilter {
    private static final Logger log = LoggerFactory.getLogger("adminLogger");

    @Override
    protected boolean preHandle(ServletRequest request, ServletResponse response) throws Exception {
        String loginToken = RestfulFilter.getToken(request);
        User user = UserUtil.getCurrentUser();
        // 非Restful方式
        if (StringUtils.isBlank(loginToken)) {
            boolean flag = super.preHandle(request, response);
            log.debug("{}退出成功", user.getUsername());
            SpringUtil.getBean(ManagementSysLogService.class).save(user.getUserId(), "退出", true, null);
            return flag;
        } else {
            ManagementTokenManager managementTokenManager = SpringUtil.getBean(ManagementTokenManager.class);
            boolean flag = managementTokenManager.deleteToken(loginToken);
            if (flag) {
                RestfulFilter.writeResponse(WebUtils.toHttp(response), HttpStatus.OK.value(), SUCCESS_INFO);
                log.debug("{}退出成功", user.getUsername());
            } else {
                RestfulFilter.writeResponse(WebUtils.toHttp(response), HttpStatus.BAD_REQUEST.value(), ERR_INFO);
            }
            SpringUtil.getBean(ManagementSysLogService.class).save(user.getUserId(), "token方式退出", flag, null);
            return false;
        }
    }

    private static String SUCCESS_INFO = JSONObject.toJSONString(new ResponseInfo(HttpStatus.OK.value() + "", "退出成功"));
    private static String ERR_INFO = JSONObject.toJSONString(new ResponseInfo(HttpStatus.BAD_REQUEST.value() + "", "退出失败,token不存在"));
}