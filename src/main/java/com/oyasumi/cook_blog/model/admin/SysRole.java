package com.oyasumi.cook_blog.model.admin;

import javax.persistence.*;

/**
 * 系统角色类
 */
@Entity
@Table(name = "sys_role")
public class SysRole {

    /**
     * 角色ID
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", length = 50)
    private Long id;

    /**
     * 角色名
     */
    @Column(name = "role_name", length = 50, nullable = false, unique = true)
    private String roleName;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    @Override
    public String toString() {
        return "SysRole{" +
                "id=" + id +
                ", roleName='" + roleName + '\'' +
                '}';
    }
}
