package com.oyasumi.cook_blog.admin;

import com.oyasumi.cook_blog.model.admin.SysPermission;
import com.oyasumi.cook_blog.repository.admin.SysPermissionRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SysPermissionTest {

    @Autowired
    SysPermissionRepository sysPermissionRepository;

    @Test
    public void testAddPermission() {
        SysPermission sysPermission = new SysPermission();
        sysPermission.setName("ROOT_DIR_PERMISSION");
        sysPermission.setDescription("Permission refers to root dir access");
        sysPermission.setUrl("/");
        sysPermission.setParentId(null);
        sysPermissionRepository.save(sysPermission);

        sysPermission = new SysPermission();
        sysPermission.setName("ADMIN_DIR_PERMISSION");
        sysPermission.setDescription("Permission refers to admin dir access");
        sysPermission.setUrl("/admin");
        sysPermission.setParentId(null);
        sysPermissionRepository.save(sysPermission);
    }

}
