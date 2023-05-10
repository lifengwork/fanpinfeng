/**
 * Copyright (c) 2019-2023 SAAS开源 All rights reserved.
 * <p>
 * SAAS系统设计研发交流
 * <p>
 * https://www.maibaduoduo.com
 */
package com.maibaduoduo.order.controller;

import com.maibaduoduo.configuration.utils.RedisUtils;
import com.maibaduoduo.sdo.UserSDO;
import com.maibaduoduo.system.facade.api.SystemFacade;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * AppController公共组件
 *
 * @author Mark sunlightcs@gmail.com
 */
public abstract class BaseController {
    protected Logger logger = LoggerFactory.getLogger(getClass());
    private SystemFacade systemFacade;
    private RedisUtils redisUtils;

    protected BaseController(SystemFacade systemFacade, RedisUtils redisUtils) {
        this.systemFacade = systemFacade;
        this.redisUtils = redisUtils;
    }

    protected HttpServletRequest getRequest() {
        return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
    }

    protected UserSDO getUserInfo() {
        systemFacade.infoByMobile("1231233455");
        String cacheKey = getRequest().getHeader("mobile");
        String mobile = redisUtils.get(StringUtils.isEmpty(cacheKey) ? "defaultempty" : cacheKey);
        UserSDO userSDO = null;
        if (StringUtils.isNotEmpty(mobile)) {
            userSDO = redisUtils.get(mobile,UserSDO.class);
        }
        return userSDO;
    }
}
