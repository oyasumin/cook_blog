package com.oyasumi.cook_blog.admin;

import com.oyasumi.cook_blog.model.SysUser;
import com.oyasumi.cook_blog.repository.admin.SysUserRepository;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SysUserTest {

    @Autowired
    private SysUserRepository sysUserRepository;

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
        sysUser.setPassword("2wsx@$EDC");
        sysUser.setLastLoginTime(new Date());
        sysUser.setStatus(1);
        sysUserRepository.save(sysUser);

        sysUser = new SysUser();
        sysUser.setNickName("cloudinshef");
        sysUser.setFullName("折木奉太郎");
        sysUser.setMobileNo("18818217471");
        sysUser.setEmail("kotarin@sheffield.ac.uk");
        sysUser.setUsername("kotarin");
        sysUser.setPassword("1qaz@WSX");
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

    @After
    public void after() {
    }

}
