/**
 * Copyright (c) 2019-2023 SAAS开源 All rights reserved.
 *
 * SAAS系统设计研发交流
 *
 * https://www.maibaduoduo.com
 */
package com.maibaduoduo.web.controller;

import com.maibaduoduo.common.utils.RedisUtils;
import com.maibaduoduo.common.utils.SystemSpringContextUtils;
import com.maibaduoduo.sys.entity.SysCaptchaEntity;
import com.maibaduoduo.sys.entity.SysUserEntity;
import org.apache.shiro.SecurityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * Controller公共组件
 *
 * @author Mark sunlightcs@gmail.com
 */
public abstract class AbstractController {
    protected Logger logger = LoggerFactory.getLogger(getClass());

    protected SysUserEntity getUser() {
        return (SysUserEntity) SecurityUtils.getSubject().getPrincipal();
    }

    protected RedisUtils redisUtils = null;

    protected Long getUserId() {
        return getUser().getUserId();
    }

    protected HttpServletRequest getRequest() {
        return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
    }

    protected boolean validate(SysCaptchaEntity captchaEntity, String code) {
        redisUtils = (RedisUtils) SystemSpringContextUtils.getBean("redisUtils");
        if (captchaEntity == null) {
            return false;
        }
        if (captchaEntity.getCode().equalsIgnoreCase(code)) {
            redisUtils.delete(captchaEntity.getUuid());
            return true;
        }
        return false;
    }
}
