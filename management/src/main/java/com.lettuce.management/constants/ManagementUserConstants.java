package com.lettuce.management.constants;

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
 * 用户相关常量
 *
 * @author Hosmos
 * @date 2021年07月08日
 */
public interface ManagementUserConstants {
    /**
     * 加密次数
     */
    int HASH_ITERATIONS = 3;
    String LOGIN_USER = "login_user";
    String USER_PERMISSIONS = "user_permissions";
    /**
     * 登陆token(nginx中默认header无视下划线)
     */
    String LOGIN_TOKEN = "login-token";
    /**
     * 文件类型
     */
    Byte CAROUSEL = 0;
    Byte GOOD_COVER = 1;
    Byte GOOD_INFO = 2;
    Byte CATEGORY = 3;
}
