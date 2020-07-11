package com.oyasumi.cook_blog.admin;

import com.oyasumi.cook_blog.model.admin.SysRole;
import com.oyasumi.cook_blog.repository.admin.SysRoleRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SysRoleTest {

    @Autowired
    private SysRoleRepository sysRoleRepository;

    @Test
    public void testAddSysRole() {
        SysRole sysRole = new SysRole();
        sysRole.setRoleName("一级管理员");
        sysRoleRepository.save(sysRole);
        sysRole = new SysRole();
        sysRole.setRoleName("二级管理员");
        sysRoleRepository.save(sysRole);
    }

}
