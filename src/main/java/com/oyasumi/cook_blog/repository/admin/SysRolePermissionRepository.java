package com.oyasumi.cook_blog.repository.admin;

import com.oyasumi.cook_blog.model.admin.SysRolePermission;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SysRolePermissionRepository extends JpaRepository<SysRolePermission, Long> {
}
