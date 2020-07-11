package com.oyasumi.cook_blog.service;

import com.oyasumi.cook_blog.model.admin.SysPermission;
import com.oyasumi.cook_blog.model.admin.SysUser;
import com.oyasumi.cook_blog.model.admin.SysUserDetails;
import com.oyasumi.cook_blog.repository.admin.SysPermissionRepository;
import com.oyasumi.cook_blog.repository.admin.SysUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SysUserDetailsService implements UserDetailsService {

    @Autowired
    SysUserRepository sysUserRepository;
    @Autowired
    SysPermissionRepository sysPermissionRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        SysUser sysUser = sysUserRepository.findByUsername(username);
        if (sysUser == null) {
            throw new UsernameNotFoundException(username + "not found!");
        }
        List<SysPermission> permissions = sysPermissionRepository.findPermissionByUserId(sysUser.getId());
        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
        for (SysPermission permission : permissions) {
            if (permission != null && permission.getName() != null) {
                GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(permission.getName());
                grantedAuthorities.add(grantedAuthority);
            }
        }
        return new SysUserDetails(sysUser, grantedAuthorities);
    }
}

