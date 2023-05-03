/**
 * Copyright (c) 2019-2023 SAAS开源 All rights reserved.
 *
 * SAAS系统设计研发交流
 *
 * https://www.maibaduoduo.com
 */
package com.maibaduoduo.app.resolver;

import com.maibaduoduo.app.annotation.LoginUser;
import com.maibaduoduo.app.service.AppUserService;
import com.maibaduoduo.interceptor.AuthorizationInterceptor;
import com.maibaduoduo.sys.entity.SysUserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import java.util.Objects;

@Component
public class LoginUserHandlerMethodArgumentResolver implements HandlerMethodArgumentResolver {
    @Autowired
    private AppUserService appUserService;
    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.getParameterType().isAssignableFrom(SysUserEntity.class) && parameter.hasParameterAnnotation(LoginUser.class);
    }
    /**
     * 动态解析绑定用户信息
     * @param parameter
     * @param container
     * @param request
     * @param factory
     * @return
     * @throws Exception
     */
    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer container,
                                  NativeWebRequest request, WebDataBinderFactory factory) throws Exception {
        Object user = request.getAttribute(AuthorizationInterceptor.USERINFO, RequestAttributes.SCOPE_REQUEST);
        if(Objects.isNull(user)){
            return null;
        }
        return appUserService.getById((Long)user);
    }
}
