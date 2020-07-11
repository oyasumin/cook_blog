package com.oyasumi.cook_blog.model.admin;

import javax.persistence.*;

/**
 * 系统角色与权限
 */
@Entity
@Table(name = "sys_role_permission")
public class SysRolePermission {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", length = 50)
    private Long id;

    @Column(name = "role_id", length = 50, nullable = false)
    private Long roleId;

    @Column(name = "permission_id", length = 50, nullable = false)
    private Long permissionId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public Long getPermissionId() {
        return permissionId;
    }

    public void setPermissionId(Long permissionId) {
        this.permissionId = permissionId;
    }

    @Override
    public String toString() {
        return "SysRolePermission{" +
                "id=" + id +
                ", roleId=" + roleId +
                ", permissionId=" + permissionId +
                '}';
    }
}
