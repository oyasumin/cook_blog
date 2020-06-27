package com.oyasumi.cook_blog.repository.admin;

import com.oyasumi.cook_blog.model.SysUser;
import com.oyasumi.cook_blog.model.SysUserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SysUserRoleRepository extends JpaRepository<SysUserRole, String> {

    @Query("SELECT u FROM SysUser u ,SysUserRole ur WHERE u.id = ur.userId AND ur.roleId = :roleId")
    List<SysUser> findUsersByRole1(@Param("roleId") Long roleId);

    @Query(nativeQuery = true, value = "SELECT u.* FROM sys_user u, sys_user_role ur WHERE u.id = ur.user_id AND ur.role_id = :roleId")
    List<SysUser> findUsersByRole2(@Param("roleId") Long roleId);

}
