package com.lettuce.management.controller;

import com.lettuce.management.annotation.LogAnnotation;
import com.lettuce.management.dto.Token;
import com.lettuce.management.entity.User;
import com.lettuce.management.service.ManagementTokenManager;
import com.lettuce.management.utils.UserUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

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
 * @description 登陆controller层
 * @date 2021年07月13日
 */
@Api(value = "login", tags = "登录接口")
@RestController
@RequestMapping("/management/dict")
public class ManagementLoginController {
    private static final Logger logger = LoggerFactory.getLogger(ManagementLoginController.class);
    @Resource
    private ManagementTokenManager managementTokenManager;
    @Resource
    private ServerProperties serverProperties;

    @LogAnnotation
    @ApiOperation(value = "web端登陆")
    @PostMapping("/sys/login")
    public void login(String username, String password) {
        UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(username, password);
        SecurityUtils.getSubject().login(usernamePasswordToken);
        // 设置shiro的session过期时间
        SecurityUtils.getSubject().getSession().setTimeout(serverProperties.getServlet().getSession().getTimeout().toMillis());
    }

    @LogAnnotation
    @ApiOperation(value = "Restful方式登陆,前后端分离时登录接口")
    @PostMapping("/sys/login/restful")
    public Token restfulLogin(String username, String password) {
        UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(username, password);
        SecurityUtils.getSubject().login(usernamePasswordToken);
        return managementTokenManager.saveToken(usernamePasswordToken);
    }

    @ApiOperation(value = "当前登录用户")
    @GetMapping("/sys/login")
    public User getLoginInfo() {
        return UserUtil.getCurrentUser();
    }
}
