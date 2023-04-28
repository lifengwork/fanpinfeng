/**
 * Copyright (c) 2019-2023 SAAS开源 All rights reserved.
 *
 * SAAS系统设计研发交流
 *
 * https://www.maibaduoduo.com
 */
package com.maibaduoduo.database.datasource;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSON;
import com.maibaduoduo.database.datasource.dynamic.TenantInfo;
import com.maibaduoduo.database.datasource.dynamic.properties.LoginProperties;
import com.maibaduoduo.database.datasource.utils.*;
import com.maibaduoduo.jwt.TokenUtil;
import com.maibaduoduo.jwt.common.BaseContextConstants;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import redis.clients.jedis.JedisPool;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.util.Objects;
import java.util.Set;

/**
 * 根据请求判单要执行的数据源
 * 系统启动时要把需要的数据源放入缓存
 */

public class TenantDataSource {
    private final Logger logger = LoggerFactory.getLogger(TenantDataSource.class);
    private static JedisPool jedisPool;
    private static TokenUtil tokenUtil;
    private static LoginProperties loginProperties;

    public TenantDataSource(JedisPool jedisPool,TokenUtil tokenUtil,LoginProperties loginProperties){
        this.jedisPool = jedisPool;
        this.tokenUtil = tokenUtil;
        this.loginProperties = loginProperties;
    }

    private static volatile TenantDataSource singletonDoubleCheckPattern = null;

    @Deprecated
    public static TenantDataSource instanceSingletonObject() {
        if (null == singletonDoubleCheckPattern) {
            synchronized (TenantDataSource.class) {
                if (null == singletonDoubleCheckPattern) {
                    singletonDoubleCheckPattern = new TenantDataSource(jedisPool,tokenUtil,loginProperties);
                }
            }
        }
        return singletonDoubleCheckPattern;
    }

    /**
     * @param httpServletRequest
     * @throws Exception
     * @throws ServletException
     */
    private String toDo(HttpServletRequest httpServletRequest) throws Exception, ServletException {
        AuthorizationInfo authorizationInfo = this.tenant(httpServletRequest);
        logger.info("用户认证信息：{}", JSON.toJSONString(authorizationInfo));
        if (ObjectUtil.isNotNull(authorizationInfo)) {
            Set<String> sysTenantDbUrlList = jedisPool.getResource().keys(loginProperties.getDbCachePrefix());
            TenantInfo tenantType = null;
            if (Objects.isNull(authorizationInfo)) {
                return null;
            }
            for (String te : sysTenantDbUrlList) {
                tenantType = BeanUtils.stringToBean(jedisPool.getResource().get(te), TenantInfo.class);
                if (authorizationInfo.getTenantId().equals(tenantType.getTenantId())) {
                    return tenantType.getDbName();
                }
            }
        }
        return null;
    }

    public AuthorizationInfo tenant(HttpServletRequest httpServletRequest){
        String tenant=null;
        com.maibaduoduo.jwt.model.AuthorizationInfo authJwtInfo=null;
        if (UserAgentUtils.isComputer(httpServletRequest)) {
            tenant = httpServletRequest.getHeader(loginProperties.getPcTokenName());//userName
            if (StringUtils.isEmpty(tenant) ||(BaseContextConstants.BEARER_HEADER_PREFIX+"null").equals(tenant)) {
                return null;
            }
            if (!loginProperties.getPcUrI().equals(httpServletRequest.getRequestURI())) {
                authJwtInfo = tokenUtil.parseJWT(tenant);
            }else{
                //用户注册或者登录时会用户信息放入缓存，这里获取
                return BeanUtils.stringToBean(jedisPool.getResource().get(StrUtil.subAfter(tenant, BaseContextConstants.BEARER_HEADER_PREFIX, false)), AuthorizationInfo.class);
            }
        }
        if (UserAgentUtils.isMobileOrTablet(httpServletRequest)
                || "Unknown".equals(UserAgentUtils.getUserAgent(httpServletRequest)
                .getOperatingSystem().getDeviceType().getName())) {
            tenant = httpServletRequest.getHeader(loginProperties.getAppTokenName());//mobile
            if (StringUtils.isEmpty(tenant)||(BaseContextConstants.BEARER_HEADER_PREFIX+"null").equals(tenant)) {
                return null;
            }
            if (!loginProperties.getAppUri().equals(httpServletRequest.getRequestURI())) {
                authJwtInfo = tokenUtil.parseJWT(tenant);
            }else{
                //用户注册或者登录时会用户信息放入缓存，这里获取
                return BeanUtils.stringToBean(jedisPool.getResource().get(StrUtil.subAfter(tenant, BaseContextConstants.BEARER_HEADER_PREFIX, false)), AuthorizationInfo.class);
            }
        }
        AuthorizationInfo authorizationInfo = new AuthorizationInfo();
        authorizationInfo.setMobile(authJwtInfo.getMobile());
        authorizationInfo.setTenantId(authJwtInfo.getTenantId());
        authorizationInfo.setUserName(authJwtInfo.getUserName());
        authorizationInfo.setUserId(String.valueOf(authJwtInfo.getUserId()));
        return authorizationInfo;
    }

    public String getCurrentTenantDatasource() throws Exception {
        String currentDs=null;
        if (Objects.nonNull((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())) {
            currentDs = toDo(((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest());
        } else if (StringUtils.isNotEmpty(DynamicDataSourceContextHolder.getDataSourceKey())) {
            currentDs = DynamicDataSourceContextHolder.getDataSourceKey();
        }
        return currentDs;
    }

    /**
     * 共享租户属性
     */
    @Data
    public static class AuthorizationInfo {
        private String userId;
        private String tenantId;
        private String userName;
        private String mobile;
    }

}

