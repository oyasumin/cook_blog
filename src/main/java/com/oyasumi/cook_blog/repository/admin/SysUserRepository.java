package com.oyasumi.cook_blog.repository.admin;

import com.oyasumi.cook_blog.model.SysUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SysUserRepository extends JpaRepository<SysUser, String> {

    SysUser findByUsername(String username);

    SysUser findByUsernameAndAndPassword(String username, String password);

    SysUser findByMobileNo(String mobileNo);

    // 用 PQL 的语法来定义一个查询。其中两个参数名字由语句中的 : 后面的字串来决定
    @Query("SELECT u FROM SysUser u WHERE u.fullName = :fullName1 OR u.fullName = :fullName2")
    List<SysUser> findMultipleName1(@Param("fullName1") String fullName1, @Param("fullName2") String fullName2);

    // 采用原生 SQL 语句的方式来编写查询
    @Query(nativeQuery = true, value = "SELECT * FROM sys_user WHERE full_name = :fullName1  OR full_name = :fullName2 ")
    List<SysUser> findMultipleName2(@Param("fullName1") String fullName1, @Param("fullName2") String fullName2);

}
