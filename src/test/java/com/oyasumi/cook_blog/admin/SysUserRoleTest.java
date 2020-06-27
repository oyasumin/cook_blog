package com.oyasumi.cook_blog.admin;

import com.oyasumi.cook_blog.model.SysRole;
import com.oyasumi.cook_blog.model.SysUser;
import com.oyasumi.cook_blog.model.SysUserRole;
import com.oyasumi.cook_blog.repository.admin.SysRoleRepository;
import com.oyasumi.cook_blog.repository.admin.SysUserRepository;
import com.oyasumi.cook_blog.repository.admin.SysUserRoleRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SysUserRoleTest {

    @Autowired
    private SysUserRoleRepository sysUserRoleRepository;
    @Autowired
    private SysRoleRepository sysRoleRepository;
    @Autowired
    private SysUserRepository sysUserRepository;

    @Test
    public void testAddUserRoleRelation() {
        SysRole sysRole = new SysRole();
        sysRole.setRoleName("一级管理员");
        sysRoleRepository.save(sysRole);
        sysRole = new SysRole();
        sysRole.setRoleName("二级管理员");
        sysRoleRepository.save(sysRole);

        SysUser sysUser = sysUserRepository.findByUsername("kotarin");
        String userId = sysUser.getId();

        SysUserRole sysUserRole = new SysUserRole();
        sysUserRole.setUserId(userId);
        sysUserRole.setRoleId(2L);
        sysUserRoleRepository.save(sysUserRole);
    }

    @Test
    public void testQueryUsersByRole() {
        List<SysUser> sysUserList = sysUserRoleRepository.findUsersByRole1(2L);
        for (SysUser sysUser : sysUserList) {
            System.out.println("UserName: " + sysUser.getFullName());
            System.out.println("MobileNO: " + sysUser.getMobileNo());
        }
    }

}
