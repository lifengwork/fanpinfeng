/**
 * Copyright (c) 2019-2023 SAAS开源 All rights reserved.
 *
 * SAAS系统设计研发交流
 *
 * https://www.maibaduoduo.com
 */
package com.maibaduoduo.app.interceptor;

import com.maibaduoduo.common.config.BaseContextHandler;
import com.maibaduoduo.common.exception.RRException;
import com.maibaduoduo.common.utils.RedisUtils;
import com.maibaduoduo.configuration.props.UserAgentUtils;
import com.maibaduoduo.database.datasource.utils.JwtUtils;
import io.jsonwebtoken.Claims;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 权限(Token)验证
 */
@Component
public class AuthorizationInterceptor extends HandlerInterceptorAdapter {
    @Autowired
    private RedisUtils redisUtils;
    @Autowired
    private JwtUtils jwtUtils;

    public static final String USER_KEY = "token-app";

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        if (UserAgentUtils.isComputer(request)) {
            if(StringUtils.isEmpty(request.getHeader("token"))){
                return false;
            }
            /*DynamicDataSourceContextHolder.setDataSourceKey(redisUtils.get(request.getHeader("token")));*/
            return true;
        }
        //获取用户Token
        String token = request.getHeader(USER_KEY);
        if(StringUtils.isBlank(token)){
            token = request.getParameter(USER_KEY);
        }
        if(StringUtils.isBlank(token)){
            throw new RRException(USER_KEY + "不能为空", HttpStatus.UNAUTHORIZED.value());
        }

        /*DynamicDataSourceContextHolder.setDataSourceKey(redisUtils.get(token));*/

        //校验TOKEN
        Claims claims = jwtUtils.getClaimByToken(token);
        if(claims == null || jwtUtils.isTokenExpired(claims.getExpiration())){
            throw new RRException(USER_KEY + "失效，请重新登录", HttpStatus.UNAUTHORIZED.value());
        }
        String claim=claims.getSubject();
        if(StringUtils.isNotEmpty(claim)){
            request.setAttribute(USER_KEY, Long.parseLong(claim.split(",")[1]));
            BaseContextHandler.set("token-app",token);
        }
        return true;
    }
}
