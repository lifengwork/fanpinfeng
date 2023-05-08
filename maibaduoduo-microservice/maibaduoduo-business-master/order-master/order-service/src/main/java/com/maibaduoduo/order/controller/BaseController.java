/**
 * Copyright (c) 2019-2023 SAAS开源 All rights reserved.
 *
 * SAAS系统设计研发交流
 *
 * https://www.maibaduoduo.com
 */
package com.maibaduoduo.order.controller;

import com.maibaduoduo.configuration.utils.R;
import com.maibaduoduo.system.facade.api.SystemFacade;
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

    protected BaseController(SystemFacade systemFacade){
        this.systemFacade = systemFacade;
    }
    protected HttpServletRequest getRequest() {
        return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
    }
    protected R getUserInfo(){
      return  R.ok(systemFacade.infoByMobile(getRequest().getHeader("mobile")));
    }
}
