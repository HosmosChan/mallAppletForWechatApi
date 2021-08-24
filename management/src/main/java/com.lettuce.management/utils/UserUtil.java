package com.lettuce.management.utils;

import com.lettuce.management.constants.ManagementUserConstants;
import com.lettuce.management.entity.Permission;
import com.lettuce.management.entity.User;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;

import java.util.List;

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
 * 用户工具类
 *
 * @author Hosmos
 * @date 2021年07月08日
 */
public class UserUtil {
    public static User getCurrentUser() {
        User user = (User) getSession().getAttribute(ManagementUserConstants.LOGIN_USER);
        return user;
    }

    public static void setUserSession(User user) {
        getSession().setAttribute(ManagementUserConstants.LOGIN_USER, user);
    }

    @SuppressWarnings("unchecked")
    public static List<Permission> getCurrentPermissions() {
        List<Permission> list = (List<Permission>) getSession().getAttribute(ManagementUserConstants.USER_PERMISSIONS);
        return list;
    }

    public static void setPermissionSession(List<Permission> list) {
        getSession().setAttribute(ManagementUserConstants.USER_PERMISSIONS, list);
    }

    public static Session getSession() {
        Subject currentUser = SecurityUtils.getSubject();
        Session session = currentUser.getSession();
        return session;
    }
}