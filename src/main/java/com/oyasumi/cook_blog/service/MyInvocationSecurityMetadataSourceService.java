package com.oyasumi.cook_blog.service;

import com.oyasumi.cook_blog.model.admin.SysPermission;
import com.oyasumi.cook_blog.repository.admin.SysPermissionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * Store and can identify the ConfigAttributes that applies to a given secure object invocation.
 * 存储和识别对应于一个给定安全对象调用的权限信息（规则）
 */
@Service
public class MyInvocationSecurityMetadataSourceService implements FilterInvocationSecurityMetadataSource {

    @Autowired
    private SysPermissionRepository sysPermissionRepository;

    /**
     * key为url地址，value为存储ConfigAttribute的列表，列表里只有一个ConfigAttribute的值
     */
    private HashMap<String, Collection<ConfigAttribute>> map = null;

    /**
     * 加载权限表中的所有权限
     */
    public void loadPermissionDefines() {
        map = new HashMap<>();
        // ConfigAttribute（permission的name）为访问规则
        Collection<ConfigAttribute> array;
        ConfigAttribute ca;
        // 找出目前所有的permissions信息
        List<SysPermission> permissions = sysPermissionRepository.findAll();
        for (SysPermission permission : permissions) {
            array = new ArrayList<>();
            ca = new SecurityConfig(permission.getName());
            // 此处只添加了权限的名字，其实还可以添加更多权限的信息
            // 此处添加的信息将会作为MyAccessDecisionManager类的decide的第三个参数
            array.add(ca);
            // 用权限的getUrl() 作为map的key，用ConfigAttribute的集合作为 value
            map.put(permission.getUrl(), array);
        }
    }

    /**
     * 此方法是为了判定用户请求的url 是否在权限表中，如果在权限表中，则返回给 decide 方法，用来判定用户是否有此权限。
     * 如果不在权限表中则放行。
     *
     * Accesses the ConfigAttributes that apply to a given secure object.
     * 对应于一个给定安全对象的权限规则
     *
     * 只要权限表中有一个url与请求匹配就返回该url对应的ConfigAttribute集合（本例中集合只有一个元素即permission的name）
     *
     * @param o
     * @return
     * @throws IllegalArgumentException
     */
    @Override
    public Collection<ConfigAttribute> getAttributes(Object o) throws IllegalArgumentException {
        if (map == null) {
            loadPermissionDefines();
        }
        // object 中包含用户请求的request 信息，the object being secured
        HttpServletRequest request = ((FilterInvocation) o).getHttpRequest();
        /*
            Matcher which compares a pre-defined ant-style pattern against the URL
            ( servletPath + pathInfo) of an HttpServletRequest.
            The query string of the URL is ignored and matching is case-insensitive or case-sensitive
            depending on the arguments passed into the constructor.
            用来做ant风格模式与实际路径间匹配的
         */
        AntPathRequestMatcher matcher;
        // 存储权限表中的某个url
        String resUrl;
        // map.KeySet() returns a Set view of the keys contained in this map
        // .iterator() returns an iterator over the elements in this set.
        for (Iterator<String> iterator = map.keySet().iterator(); iterator.hasNext();) {
            // 遍历权限表中的url
            resUrl = iterator.next();
            matcher = new AntPathRequestMatcher(resUrl);
            // Returns true if the configured pattern (and HTTP-Method) match those of the
            // supplied request.
            if (matcher.matches(request)) {
                return map.get(resUrl);
            }
        }
        return null;
    }

    /**
     * If available, returns all of the ConfigAttributes defined by the implementing class.
     *
     * 返回权限表中的所有权限（ConfigAttribute集合形式）
     * @return
     */
    @Override
    public Collection<ConfigAttribute> getAllConfigAttributes() {
        return null;
    }

    /**
     * Indicates whether the SecurityMetadataSource implementation is able to provide ConfigAttributes
     * for the indicated secure object type.
     *
     * 此SecurityMetadataSource的实现是否可以提供针对安全对象类型的权限规则
     *
     * @param aClass
     * @return
     */
    @Override
    public boolean supports(Class<?> aClass) {
        return true;
    }
}
