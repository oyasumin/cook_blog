package com.oyasumi.cook_blog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.SecurityMetadataSource;
import org.springframework.security.access.intercept.AbstractSecurityInterceptor;
import org.springframework.security.access.intercept.InterceptorStatusToken;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.stereotype.Service;

import javax.servlet.*;
import java.io.IOException;

/**
 * FilterChain的最后一个：e.g. FilterSecurityInterceptor
 *
 * Ensure the proper startup configuration of the security interceptor.
 * It will also implement the proper handling of secure object invocations.
 *
 * 1.Obtain the Authentication object from the SecurityContextHolder.
 * 2.Determine if the request relates to a secured or public invocation by looking up the secure object request against the SecurityMetadataSource.
 * 3.1.For an invocation that is secured (there is a list of ConfigAttributes for the secure object invocation)
 * 3.2.For an invocation that is public (there are no ConfigAttributes for the secure object invocation)
 * 4.Control again returns to the concrete subclass, along with the Object that should be returned to the caller. The subclass will then return that result or exception to the original caller.
 */
@Service
public class MyFilterSecurityInterceptor extends AbstractSecurityInterceptor implements Filter {

    @Autowired
    private FilterInvocationSecurityMetadataSource securityMetadataSource;

    /**
     * 在setter方法上加@Autowired注解会自动将入参的bean进行连接
     * 此处作用即：将MyAccessDecisionManager这个bean自动赋值给该类的成员变量 AccessDecisionManager
     * @param myAccessDecisionManager
     */
    @Autowired
    public void setMyAccessDecisionManager(MyAccessDecisionManager myAccessDecisionManager) {
        super.setAccessDecisionManager(myAccessDecisionManager);
    }

    /**
     * AbstractSecurityInterceptor的方法
     *
     * Indicates the type of secure objects the subclass will be presenting to the abstract parent for processing.
     * This is used to ensure collaborators wired to the AbstractSecurityInterceptor all support the indicated secure object class.
     *
     * 返回子类呈现给父类处理的安全对象
     *
     * @return
     * the type of secure object the subclass provides services for
     */
    @Override
    public Class<?> getSecureObjectClass() {
        return FilterInvocation.class;
    }

    /**
     * AbstractSecurityInterceptor的方法
     *
     * @return
     */
    @Override
    public SecurityMetadataSource obtainSecurityMetadataSource() {
        return this.securityMetadataSource;
    }

    /**
     * Fileter接口方法
     * @param filterConfig
     * @throws ServletException
     */
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    /**
     * Filter接口方法
     *
     * Method that is actually called by the filter chain. Simply delegates（委托/代表） to the
     * {@link #invoke(FilterInvocation)} method.
     *
     * @param servletRequest
     * @param servletResponse
     * @param filterChain
     * @throws IOException
     * @throws ServletException
     */
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        // FilterInvocation: Holds objects associated with a HTTP filter.
        // 这个类的作用本身很简单，就是把doFilter传进来的request,response和FilterChain对象保存起来，供FilterSecurityInterceptor的处理代码调用
        FilterInvocation fi = new FilterInvocation(servletRequest, servletResponse, filterChain);
        invoke(fi);
    }

    /**
     * 拦截方法
     *
     * @param fi
     * @throws IOException
     * @throws ServletException
     */
    public void invoke(FilterInvocation fi) throws IOException, ServletException {
        /*
            fi里面有一个被拦截的url，
            里面调用MyInvocationSecurityMetadataSource的getAttributes(Object object)这个方法获取fi对应的所有权限
            再调用MyAccessDecisionManager的decide方法来校验用户的权限是否足够
         */
        // InterceptorStatusToken（拦截器状态令牌）:
        // This class reflects the status of the security interception, so that the final call to
        // AbstractSecurityInterceptor.afterInvocation(InterceptorStatusToken, Object) can tidy up correctly.
        // 该方法内有：
        // this.obtainSecurityMetadataSource().getAttributes(object);
        // this.accessDecisionManager.decide(authenticated, object, attributes);
        InterceptorStatusToken token = super.beforeInvocation(fi);
        try {
            // 执行下一个拦截器
            // doFilter() 方法，该方法的作用是让 Filter 链上的当前过滤器放行，使请求进入下一个 Filter
            fi.getChain().doFilter(fi.getRequest(), fi.getResponse());
        } finally {
            // Completes the work of the AbstractSecurityInterceptor after the secure object invocation has been completed.
            super.afterInvocation(token, null);
        }
    }


    /**
     * Filter接口方法
     */
    @Override
    public void destroy() {
    }
}
