package com.oyasumi.cook_blog.model.admin;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Date;

public class SysUserDetails implements UserDetails {

    private SysUser sysUser;
    private Collection<? extends GrantedAuthority> authorities;

    public SysUserDetails(SysUser sysUser, Collection<? extends GrantedAuthority> authorities) {
        this.sysUser = sysUser;
        this.authorities = authorities;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return this.sysUser.getPassword();
    }

    @Override
    public String getUsername() {
        return this.sysUser.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        Date createTime = this.sysUser.getCreateTime();
        Date lastLoginTime = this.sysUser.getLastLoginTime();
        Long duration = (lastLoginTime.getTime() - createTime.getTime()) / (24*60*60*1000);
        if (duration > 180) {
            return false;
        } else {
            return true;
        }
    }

    @Override
    public boolean isAccountNonLocked() {
        return this.sysUser.getStatus() == 1 ? true : false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return this.sysUser.getStatus() == 1 ? true : false;
    }

}
