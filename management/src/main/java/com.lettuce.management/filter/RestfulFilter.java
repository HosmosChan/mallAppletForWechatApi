package com.lettuce.management.filter;

import com.alibaba.fastjson.JSONObject;
import com.lettuce.management.constants.ManagementUserConstants;
import com.lettuce.management.dto.ResponseInfo;
import com.lettuce.management.service.ManagementTokenManager;
import com.lettuce.management.utils.SpringUtil;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.UserFilter;
import org.apache.shiro.web.util.WebUtils;
import org.springframework.http.HttpStatus;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

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
 * @description Restful方式登陆, 在参数中或者header里加参数login-token作为登陆凭证, 参数值是登陆成功后的返回值中获取
 * @date 2021年07月08日
 */
public class RestfulFilter extends UserFilter {
    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) {
        String loginToken = getToken(request);
        // 非Restful方式
        if (StringUtils.isBlank(loginToken)) {
            return super.isAccessAllowed(request, response, mappedValue);
        }
        ManagementTokenManager managementTokenManager = SpringUtil.getBean(ManagementTokenManager.class);
        UsernamePasswordToken token = managementTokenManager.getToken(loginToken);
        if (token != null) {
            try {
                Subject subject = getSubject(request, response);
                if (subject.getPrincipal() == null) {
                    subject.login(token);
                }
                return true;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    /**
     * @param request
     * @return String
     * @description 根据参数或者header获取login-token
     * @author Hosmos
     * @date 2021-07-08
     */
    public static String getToken(ServletRequest request) {
        HttpServletRequest httpServletRequest = WebUtils.toHttp(request);
        String loginToken = httpServletRequest.getParameter(ManagementUserConstants.LOGIN_TOKEN);
        if (StringUtils.isBlank(loginToken)) {
            loginToken = httpServletRequest.getHeader(ManagementUserConstants.LOGIN_TOKEN);
        }
        return loginToken;
    }

    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
        String loginToken = getToken(request);
        if (StringUtils.isBlank(loginToken)) {
            return super.onAccessDenied(request, response);
        }
        writeResponse(WebUtils.toHttp(response), HttpStatus.UNAUTHORIZED.value(), info);
        return false;
    }

    private static String info = JSONObject.toJSONString(new ResponseInfo(HttpStatus.UNAUTHORIZED.value() + "", "token不存在或者过期"));

    public static void writeResponse(HttpServletResponse response, int status, String json) {
        try {
            response.setHeader("Access-Control-Allow-Origin", "*");
            response.setHeader("Access-Control-Allow-Methods", "*");
            response.setContentType("application/json;charset=UTF-8");
            response.setStatus(status);
            response.getWriter().write(json);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}