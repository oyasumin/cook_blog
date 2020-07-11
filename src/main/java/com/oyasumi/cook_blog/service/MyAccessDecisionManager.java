package com.oyasumi.cook_blog.service;

import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Iterator;

/**
 * 访问决策管理器 AccessDecisionManager 用来进行投票决策来确定是否放行
 */
@Service
public class MyAccessDecisionManager implements AccessDecisionManager {

    /**
     * 1.decide 方法是判定是否拥有权限的决策方法
     *
     * 2.【该用户具备的】authentication 是SysUserDetailsService中循环添加GrantedAuthority权限信息生成的对象集合
     *
     * 3.object 包含客户端发起的请求的requset信息，
     * 可转换为 HttpServletRequest request = ((FilterInvocation) object).getHttpRequest();
     *
     * 4.【用户想访问的】collection 为MyInvocationSecurityMetadataSource的getAttributes(Object object)这个方法返回的结果，
     * 此方法是为了判定用户请求的url 是否在权限表中，如果在权限表中，则返回给 decide 方法，用来判定用户是否有此权限。
     * 如果不在权限表中则放行。
     *
     * 此处实现类似于AffirmativeBased投票
     *
     * @param authentication
     * @param o
     * @param collection
     * @throws AccessDeniedException
     * @throws InsufficientAuthenticationException
     */
    @Override
    public void decide(Authentication authentication, Object o, Collection<ConfigAttribute> collection) throws AccessDeniedException, InsufficientAuthenticationException {
        if (null == collection || collection.size()<=0) {
            return;
        }
        ConfigAttribute c;
        String needRole;
        for (Iterator<ConfigAttribute> iterator = collection.iterator(); iterator.hasNext();) {
            // ConfigAttribute为权限规则，内部存储permission.getName()
            c = iterator.next();
            // 获取permission.getName()，此为用户请求访问的url权限
            needRole = c.getAttribute();
            // authentication.getAuthorities()返回Collection<? extends GrantedAuthority>
            // 该用户具备哪些访问权限
            for (GrantedAuthority ga : authentication.getAuthorities()) {
                //ga.getAuthority()返回的是permission.getName(),在SimpleGrantedAuthority中该变量名为role
                //只要该用户有一个url权限和他本次请求的url权限匹配则通过
                if (needRole.trim().equals(ga.getAuthority())) {
                    return;
                }
            }
        }
        throw new AccessDeniedException("No rights");
    }

    /**
     * Indicates whether this AccessDecisionManager is able to process authorization requests presented
     * with the passed ConfigAttribute.
     *
     * 该AccessDecisionManager是否能够根据ConfigAttribute处理一同传过来的授权请求
     *
     * @param configAttribute
     * @return
     */
    @Override
    public boolean supports(ConfigAttribute configAttribute) {
        return true;
    }

    /**
     * Indicates whether the AccessDecisionManager implementation is able to provide access control decisions
     * for the indicated secured object type.
     *
     * 该AccessDecisionManager实现是否能够对安全对象类型提供访问控制决策
     *
     * @param aClass
     * @return
     */
    @Override
    public boolean supports(Class<?> aClass) {
        return true;
    }
}
