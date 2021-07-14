package com.lettuce.management.config;

import com.lettuce.management.dao.ManagementPermissionDao;
import com.lettuce.management.dao.ManagementRoleDao;
import com.lettuce.management.entity.Permission;
import com.lettuce.management.entity.Role;
import com.lettuce.management.entity.User;
import com.lettuce.management.service.ManagementUserService;
import com.lettuce.management.utils.SpringUtil;
import com.lettuce.management.utils.UserUtil;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.SimplePrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

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
 * @description 用户Shiro权限领域配置
 * @date 2021年07月08日
 */
public class MyShiroRealm extends AuthorizingRealm {
    private static final Logger log = LoggerFactory.getLogger("adminLogger");

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        UsernamePasswordToken usernamePasswordToken = (UsernamePasswordToken) token;
        String username = usernamePasswordToken.getUsername();
        ManagementUserService managementUserService = SpringUtil.getBean(ManagementUserService.class);
        User user = managementUserService.getUser(username);
        if (user == null) {
            throw new UnknownAccountException("用户名不存在");
        }
        if (!user.getPassword().equals(managementUserService.passwordEncoder(new String(usernamePasswordToken.getPassword()), user.getSalt()))) {
            throw new IncorrectCredentialsException("密码错误");
        }
        if (user.getStatus() != User.Status.VALID) {
            throw new IncorrectCredentialsException("无效状态，请联系管理员");
        }
        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(user, user.getPassword(), ByteSource.Util.bytes(user.getSalt()), getName());
        UserUtil.setUserSession(user);
        return authenticationInfo;
    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        log.debug("权限配置");
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        User user = UserUtil.getCurrentUser();
        List<Role> roles = SpringUtil.getBean(ManagementRoleDao.class).listByUserId(user.getTid());
        Set<String> roleNames = roles.stream().map(Role::getName).collect(Collectors.toSet());
        authorizationInfo.setRoles(roleNames);
        List<Permission> permissionList = SpringUtil.getBean(ManagementPermissionDao.class).listByUserId(user.getTid());
        UserUtil.setPermissionSession(permissionList);
        Set<String> permissions = permissionList.stream().filter(p -> !StringUtils.isEmpty(p.getPermission())).map(Permission::getPermission).collect(Collectors.toSet());
        authorizationInfo.setStringPermissions(permissions);
        return authorizationInfo;
    }

    @Override
    protected Object getAuthorizationCacheKey(PrincipalCollection principals) {
        SimplePrincipalCollection principalCollection = (SimplePrincipalCollection) principals;
        Object object = principalCollection.getPrimaryPrincipal();
        if (object instanceof User) {
            User user = (User) object;
            return "authorization:cache:key:users:" + user.getTid();
        }
        return super.getAuthorizationCacheKey(principals);
    }
}