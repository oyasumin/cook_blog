package com.oyasumi.cook_blog.admin;

import com.oyasumi.cook_blog.model.admin.SysPermission;
import com.oyasumi.cook_blog.model.admin.SysUser;
import com.oyasumi.cook_blog.repository.admin.SysPermissionRepository;
import com.oyasumi.cook_blog.repository.admin.SysUserRepository;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SysUserTest {

    @Autowired
    private SysUserRepository sysUserRepository;
    @Autowired
    private SysPermissionRepository sysPermissionRepository;

//    // 在测试用例之前执行准备的代码
//    @Before
//    public void before() {
//        SysUser sysUser = new SysUser();
//        sysUser.setNickName("thunderinshef");
//        sysUser.setFullName("折木晴");
//        sysUser.setMobileNo("18818217478");
//        sysUser.setEmail("hotarin@sheffield.ac.uk");
//        sysUser.setUsername("houtari");
//        sysUser.setPassword("1qaz@$WSX");
////        sysUser.setCreateTime(new Date());
////        sysUser.setUpdateTime(new Date());
//        sysUser.setStatus(1);
//        sysUserRepository.save(sysUser);
//    }

    @Test
    public void testAddUser() {
        SysUser sysUser = new SysUser();
        sysUser.setNickName("carinshef");
        sysUser.setFullName("千反田爱瑠");
        sysUser.setMobileNo("18827919299");
        sysUser.setEmail("wasirui@sheffield.ac.uk");
        sysUser.setUsername("wasirui");
        String password = "2wsx@$EDC";
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = passwordEncoder.encode(password);
        sysUser.setPassword(encodedPassword);
        sysUser.setLastLoginTime(new Date());
        sysUser.setStatus(1);
        sysUserRepository.save(sysUser);

        sysUser = new SysUser();
        sysUser.setNickName("cloudinshef");
        sysUser.setFullName("折木奉太郎");
        sysUser.setMobileNo("18818217471");
        sysUser.setEmail("kotarin@sheffield.ac.uk");
        sysUser.setUsername("kotarin");
        password = "1qaz@WSX";
        encodedPassword = passwordEncoder.encode(password);
        sysUser.setPassword(encodedPassword);
        sysUser.setLastLoginTime(new Date());
        sysUser.setStatus(1);
        sysUserRepository.save(sysUser);
    }

    @Test
    public void testQueryAllUser() {
        List<SysUser> userList = sysUserRepository.findAll();
        for (SysUser sysUser : userList) {
            System.out.println(sysUser);
        }
    }

    @Test
    public void testQueryOneUserByUsername() {
        SysUser sysUser = sysUserRepository.findByUsername("wasirui");
        if (sysUser != null) {
            System.out.println("FullName: " + sysUser.getFullName());
            System.out.println("MobileNO: " + sysUser.getMobileNo());
        }
    }

    @Test
    public void testQueryOneUserByMobileNo() {
        SysUser sysUser = sysUserRepository.findByMobileNo("18818217471");
        if (sysUser != null) {
            System.out.println("FullName: " + sysUser.getFullName());
            System.out.println("MobileNO: " + sysUser.getMobileNo());
        }
    }

    @Test
    public void testQueryTwoUsersName() {
        List<SysUser> sysUserList = sysUserRepository.findMultipleName1("千反田爱瑠", "折木奉太郎");
        for (SysUser sysUser : sysUserList) {
            System.out.println(sysUser);
        }
    }

    @Test
    public void testDeleteAllUser() {
        sysUserRepository.deleteAll();
    }

    /**
     * 根据userId查询该用户的权限信息
     */
    @Test
    public void testFindPermissionByUserId() {
        SysUser sysUser = sysUserRepository.findByUsername("wasirui");
        String userId = sysUser.getId();
        List<SysPermission> permissionList = sysPermissionRepository.findPermissionByUserId(userId);
        System.out.println("Permissions of wasirui: ");
        for (SysPermission sysPermission : permissionList) {
            System.out.println(sysPermission);
        }

        sysUser = sysUserRepository.findByUsername("kotarin");
        userId = sysUser.getId();
        permissionList = sysPermissionRepository.findPermissionByUserId(userId);
        System.out.println("Permissions of kotarin: ");
        for (SysPermission sysPermission : permissionList) {
            System.out.println(sysPermission);
        }
    }

    @After
    public void after() {
    }

}
