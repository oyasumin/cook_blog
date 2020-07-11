package com.oyasumi.cook_blog.model.admin;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.hibernate.annotations.ColumnTransformer;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

/**
 * 系统用户类
 */
@Entity
@Table(name = "sys_user")
@GenericGenerator(name = "jpa-uuid", strategy = "uuid")
@EntityListeners(AuditingEntityListener.class)
public class SysUser {

    /**
     * 账号ID，UUID
     */
    @Id
    @GeneratedValue(generator = "jpa-uuid")
    @Column(name = "id", length = 50)
    private String id;

    /**
     * 账号昵称
     */
    @Column(name = "nick_name", length = 50, nullable = false, unique = true)
    private String nickName;

    /**
     * 用户姓名
     */
    @Column(name = "full_name", length = 50, nullable = false)
    @ColumnTransformer(
            read = "CAST(AES_DECRYPT(UNHEX(full_name), 'oyasumi') as char(128))",
            write = "HEX(AES_ENCRYPT(?, 'oyasumi'))"
    )
    private String fullName;

    /**
     * 用户手机号
     */
    @Column(name = "mobile_no", length = 50, nullable = false, unique = true)
    @ColumnTransformer(
            read = "CAST(AES_DECRYPT(UNHEX(mobile_no), 'oyasumi') as char(128))",
            write = "HEX(AES_ENCRYPT(?, 'oyasumi'))"
    )
    private String mobileNo;

    /**
     * 用户邮箱
     */
    @Column(name = "email", nullable = false, unique = true)
    private String email;

    /**
     * 账号名
     */
    @Column(name = "username", length = 50, nullable = false, unique = true)
    private String username;

    /**
     * 账号密码
     */
    @Column(name = "password", nullable = false)
    // aes_encrypt()加密之后是二进制数据
    // HEX()函数：返回十六进制值的字符串表示形式。注意：并不是十进制转化为十六进制数，而是转化为字符串
    // UNHEX()函数: 每对十六进制数字转化为一个字符
    @ColumnTransformer(
            read = "CAST(AES_DECRYPT(UNHEX(password), 'oyasumi') as char(128))",
            write = "HEX(AES_ENCRYPT(?, 'oyasumi'))"
    )
    private String password;

    /**
     * 账号创建时间
     */
    @Column(name = "create_time", nullable = false, updatable = false)
    @CreatedDate
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    /**
     * 账号信息上次修改时间
     */
    @Column(name = "update_time", nullable = false)
    @LastModifiedDate
    @Temporal(TemporalType.TIMESTAMP)
    private Date updateTime;

    /**
     * 账号上次成功登录时间
     */
    @Column(name = "last_login_time")
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastLoginTime;

    /**
     * 账号状态
     * 锁定：0，可用：1
     */
    @Column(name = "status", length = 10, nullable = false)
    private Integer status;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getMobileNo() {
        return mobileNo;
    }

    public void setMobileNo(String mobileNo) {
        this.mobileNo = mobileNo;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Date getLastLoginTime() {
        return lastLoginTime;
    }

    public void setLastLoginTime(Date lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "SysUser{" +
                "id=" + id +
                ", nickName='" + nickName + '\'' +
                ", fullName='" + fullName + '\'' +
                ", mobileNo='" + mobileNo + '\'' +
                ", email='" + email + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", lastLoginTime=" + lastLoginTime +
                ", status=" + status +
                '}';
    }
}
