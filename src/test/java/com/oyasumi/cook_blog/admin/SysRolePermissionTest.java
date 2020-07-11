package com.oyasumi.cook_blog.admin;

import com.oyasumi.cook_blog.model.admin.SysRolePermission;
import com.oyasumi.cook_blog.repository.admin.SysRolePermissionRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SysRolePermissionTest {

    @Autowired
    SysRolePermissionRepository sysRolePermissionRepository;

    @Test
    public void testAddRolePermissionRelation() {
        SysRolePermission sysRolePermission = new SysRolePermission();
        sysRolePermission.setRoleId(1L);
        sysRolePermission.setPermissionId(1L);
        sysRolePermissionRepository.save(sysRolePermission);

        sysRolePermission = new SysRolePermission();
        sysRolePermission.setRoleId(1L);
        sysRolePermission.setPermissionId(2L);
        sysRolePermissionRepository.save(sysRolePermission);

        sysRolePermission = new SysRolePermission();
        sysRolePermission.setRoleId(2L);
        sysRolePermission.setPermissionId(1L);
        sysRolePermissionRepository.save(sysRolePermission);
    }

}
