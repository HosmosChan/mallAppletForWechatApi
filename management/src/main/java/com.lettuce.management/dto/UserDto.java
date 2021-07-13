package com.lettuce.management.dto;

import com.lettuce.management.entity.User;

import java.util.List;

public class UserDto extends User {
    private static final long serialVersionUID = 790373628809052521L;
    private List<Long> roleIds;

    public List<Long> getRoleIds() {
        return roleIds;
    }

    public void setRoleIds(List<Long> roleIds) {
        this.roleIds = roleIds;
    }
}