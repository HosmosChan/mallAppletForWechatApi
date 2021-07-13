package com.lettuce.management.dto;

import com.lettuce.management.entity.Role;

import java.util.List;

public class RoleDto extends Role {
private static final long serialVersionUID = -1379926857568477065L;
private List<Long> permissionIds;

public List<Long> getPermissionIds() {
    return permissionIds;
}

public void setPermissionIds(List<Long> permissionIds) {
    this.permissionIds = permissionIds;
}
}