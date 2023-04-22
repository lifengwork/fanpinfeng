package com.maibaduoduo.database.datasource;

import cn.hutool.core.util.ObjectUtil;
import com.maibaduoduo.database.datasource.dynamic.TenantInfo;
import com.maibaduoduo.database.datasource.dynamic.properties.LoginProperties;
import com.maibaduoduo.database.datasource.utils.*;
import io.jsonwebtoken.Claims;
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
    private JedisPool jedisPool = (JedisPool) CommSpringContextUtil.getBean("jedisPool");
    private JwtUtils jwtUtils = (JwtUtils) CommSpringContextUtil.getBean("jwtUtils");
    private LoginProperties loginProperties = (LoginProperties) CommSpringContextUtil.getBean("loginProperties");

    private TenantDataSource() {
    }

    private static volatile TenantDataSource singletonDoubleCheckPattern = null;

    public static TenantDataSource instanceSingletonObject() {
        if (null == singletonDoubleCheckPattern) {
            synchronized (TenantDataSource.class) {
                if (null == singletonDoubleCheckPattern) {
                    singletonDoubleCheckPattern = new TenantDataSource();
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
        if (UserAgentUtils.isComputer(httpServletRequest)) {
            tenant = httpServletRequest.getHeader(loginProperties.getPcTokenName());//userName
            if (StringUtils.isEmpty(tenant) || "null".equals(tenant)) {
                return null;
            }
            if (!loginProperties.getPcUrI().equals(httpServletRequest.getRequestURI())) {
                tenant = this.parseTenant(tenant);
            }
        }
        if (UserAgentUtils.isMobileOrTablet(httpServletRequest)
                || "Unknown".equals(UserAgentUtils.getUserAgent(httpServletRequest)
                .getOperatingSystem().getDeviceType().getName())) {
            tenant = httpServletRequest.getHeader(loginProperties.getAppTokenName());//mobile
            if (StringUtils.isEmpty(tenant)) {
                return null;
            }
            if (!loginProperties.getAppUri().equals(httpServletRequest.getRequestURI())) {
                tenant = this.parseTenant(tenant);
            }
        }
        return BeanUtils.stringToBean(jedisPool.getResource().get(tenant), AuthorizationInfo.class);
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
     * 从Token中解析租户信息
     *
     * @param token
     * @return
     */
    private String parseTenant(String token) {
        String tenant = null;
        try {
            Claims claims = jwtUtils.getClaimByToken(token);
            String claim = claims.getSubject();
            if (StringUtils.isNotEmpty(claim)) {
                tenant = claim.split(",")[1];
            }
        } catch (Exception e) {
            logger.error("[无效token]");
        }
        return tenant;
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

