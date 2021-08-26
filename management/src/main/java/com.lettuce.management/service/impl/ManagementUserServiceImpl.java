package com.lettuce.management.service.impl;

import com.lettuce.management.constants.ManagementUserConstants;
import com.lettuce.management.dao.ManagementAppletDao;
import com.lettuce.management.dao.ManagementUserDao;
import com.lettuce.management.dto.UserDto;
import com.lettuce.management.entity.User;
import com.lettuce.management.service.ManagementUserService;
import com.lettuce.management.utils.UserUtil;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Map;
import java.util.UUID;

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
 * 用户实现层
 *
 * @author Hosmos
 * @date 2021年07月14日
 */
@Service
public class ManagementUserServiceImpl implements ManagementUserService {
    private static final Logger log = LoggerFactory.getLogger("adminLogger");
    @Autowired
    private ManagementUserDao managementUserDao;
    @Autowired
    private ManagementAppletDao managementAppletDao;

    @Override
    public User getUser(String username) {
        return managementUserDao.getUser(username);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public User saveUser(UserDto userDto) {
        userDto.setSalt(DigestUtils.md5Hex(UUID.randomUUID().toString() + System.currentTimeMillis() + UUID.randomUUID().toString()));
        userDto.setPassword(passwordEncoder(userDto.getPassword(), userDto.getSalt()));
        userDto.setStatus(User.Status.VALID);
        userDto.setCreateUserId(UserUtil.getCurrentUser().getId());
        managementUserDao.saveUser(userDto);
        saveUserRoles(userDto.getId(), userDto.getRoleIds());
        log.debug("新增用户{}", userDto.getUsername());
        return userDto;
    }

    @Override
    public String passwordEncoder(String credentials, String salt) {
        Object object = new SimpleHash("MD5", credentials, salt, ManagementUserConstants.HASH_ITERATIONS);
        return object.toString();
    }

    @Override
    public UserDto updateUser(UserDto userDto) {
        userDto.setGmtUserId(UserUtil.getCurrentUser().getId());
        managementUserDao.updateUser(userDto);
        saveUserRoles(userDto.getId(), userDto.getRoleIds());
        updateUserSession(userDto.getId());
        return userDto;
    }

    @Override
    public void changePassword(String username, String oldPassword, String newPassword) {
        User u = managementUserDao.getUser(username);
        if (u == null) {
            throw new IllegalArgumentException("用户不存在");
        }
        if (!u.getPassword().equals(passwordEncoder(oldPassword, u.getSalt()))) {
            throw new IllegalArgumentException("密码错误");
        }
        Long gmtUserId = UserUtil.getCurrentUser().getId();
        managementUserDao.changePassword(u.getId(), passwordEncoder(newPassword, u.getSalt()), gmtUserId);
        log.debug("修改{}的密码", username);
    }

    @Override
    public int count(Map<String, Object> params) {
        return managementUserDao.count(params);
    }

    @Override
    public List<User> list(Map<String, Object> params, Integer offset, Integer limit) {
        return managementUserDao.list(params, offset, limit);
    }

    @Override
    public User getByUserId(Long id) {
        return managementUserDao.getByUserId(id);
    }

    @Override
    public void delete(Long id) {
        Long appletId = managementAppletDao.getByUserId(id).getId();
        Byte status = 2;
        managementAppletDao.delete(appletId, UserUtil.getCurrentUser().getId(), status);
        managementUserDao.delete(id, UserUtil.getCurrentUser().getId(), status);
    }

    private void saveUserRoles(Long userId, List<Long> roleIds) {
        if (roleIds != null) {
            managementUserDao.deleteUserRole(userId);
            if (!CollectionUtils.isEmpty(roleIds)) {
                Long createUserId = UserUtil.getCurrentUser().getId();
                managementUserDao.saveUserRoles(userId, roleIds, createUserId);
            }
        }
    }

    private void updateUserSession(Long id) {
        User current = UserUtil.getCurrentUser();
        if (current.getId().equals(id)) {
            User user = managementUserDao.getByUserId(id);
            UserUtil.setUserSession(user);
        }
    }
}
