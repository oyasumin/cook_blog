package com.oyasumi.cook_blog.model.admin;

import javax.persistence.*;

/**
 * 系统权限
 */
@Entity
@Table(name = "sys_permission")
public class SysPermission {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", length = 50)
    private Long id;

    /**
     * 权限名称
     */
    @Column(name = "name", length = 80, nullable = false, unique = true)
    private String name;

    /**
     * 权限描述
     */
    @Column(name = "description", length = 100, nullable = false, unique = true)
    private String description;

    /**
     * 授权链接
     */
    @Column(name = "url", length = 100, nullable = false, unique = true)
    private String url;

    /**
     * 父节点ID
     */
    @Column(name = "parent_id")
    private Long parentId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    @Override
    public String toString() {
        return "SysPermission{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", url='" + url + '\'' +
                ", parentId=" + parentId +
                '}';
    }
}
