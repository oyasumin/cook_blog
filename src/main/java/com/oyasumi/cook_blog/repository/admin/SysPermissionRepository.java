package com.oyasumi.cook_blog.repository.admin;

import com.oyasumi.cook_blog.model.admin.SysPermission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SysPermissionRepository extends JpaRepository<SysPermission, Long> {

    /**
     * 根据userId查询该用户的权限
     * @param userId
     * @return
     */
    @Query("SELECT p FROM SysUser u " +
            "LEFT JOIN SysUserRole ur ON u.id = ur.userId " +
            "LEFT JOIN SysRole r ON ur.roleId = r.id " +
            "LEFT JOIN SysRolePermission rp ON r.id = rp.roleId " +
            "LEFT JOIN SysPermission p ON rp.permissionId = p.id " +
            "WHERE u.id = :userId ")
    List<SysPermission> findPermissionByUserId(@Param("userId") String userId);

}
