package com.lettuce.mall.controller;

import com.lettuce.mall.model.UserInfo;
import com.lettuce.mall.service.UserService;
import org.slf4j.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
 * @description 用户controller层
 * @date 2021年06月22日
 */
@RestController
@RequestMapping("/users")
public class UserController {
    private static final Logger log = LoggerFactory.getLogger("adminLogger");
    @Autowired
    private UserService userService;

    @PostMapping
    public UserInfo saveUser(@RequestBody String openid) {
        UserInfo u = userService.getUser(openid);
        if (u != null) {
            return null;
        }
        return userService.saveUser(openid);
    }

    @PostMapping
    public UserInfo updateUser(@RequestBody UserInfo user) {
        return userService.updateUser(user);
    }
}