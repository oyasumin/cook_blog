package com.oyasumi.cook_blog.model.admin;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

/**
 * 用户角色关系类
 */
@Entity
@IdClass(UserRoleId.class)
@Table(name = "sys_user_role")
public class SysUserRole {

    @Id
    private String userId;

    @Id
    private Long roleId;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }
}
