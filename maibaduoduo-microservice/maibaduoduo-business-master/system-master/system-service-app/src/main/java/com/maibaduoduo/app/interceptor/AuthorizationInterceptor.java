/**
 * Copyright (c) 2019-2023 SAAS开源 All rights reserved.
 *
 * SAAS系统设计研发交流
 *
 * https://www.maibaduoduo.com
 */
package com.maibaduoduo.app.interceptor;

import com.maibaduoduo.app.annotation.Login;
import com.maibaduoduo.common.exception.RRException;
import com.maibaduoduo.common.utils.RedisUtils;
import com.maibaduoduo.database.datasource.utils.JwtUtils;
import com.maibaduoduo.jwt.TokenUtil;
import com.maibaduoduo.jwt.model.AuthorizationInfo;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Objects;

/**
 * 权限(Token)验证
 */
@Component
public class AuthorizationInterceptor extends HandlerInterceptorAdapter {
    @Autowired
    private RedisUtils redisUtils;
    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    private TokenUtil tokenUtil;

    public static String USER_KEY = "token-app";

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Login annotation;
        if(handler instanceof HandlerMethod) {
            annotation = ((HandlerMethod) handler).getMethodAnnotation(Login.class);
        }else{
            return true;
        }
        if(annotation == null){
            return true;
        }
        String token = request.getHeader(USER_KEY);
        if(StringUtils.isBlank(token)){
            token = request.getParameter(USER_KEY);
        }
        if(StringUtils.isBlank(token)){
            throw new RRException("TOKEN" + "不能为空", HttpStatus.UNAUTHORIZED.value());
        }
        //TOKEN认证信息
        try{
            AuthorizationInfo authorizationInfo = tokenUtil.parseJWT(token);
            if(Objects.nonNull(authorizationInfo)){
                request.setAttribute(USER_KEY,authorizationInfo.getMobile());
            }
        }catch (Exception e){
            throw new RRException(e.getMessage(), HttpStatus.UNAUTHORIZED.value());
        }
        return true;
    }
}
