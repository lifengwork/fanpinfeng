/**
 * Copyright (c) 2019-2023 SAAS开源 All rights reserved.
 *
 * SAAS系统设计研发交流
 *
 * https://www.maibaduoduo.com
 */
package com.maibaduoduo.app.service;

import com.maibaduoduo.common.form.LoginForm;
import com.maibaduoduo.service.impl.SysUserServiceImpl;
import com.maibaduoduo.sys.entity.SysUserEntity;

/**
 * @Description: //TODO
 * @date: 2023/4/13 16:59
 * @Author: pm2022
 */
public abstract class AppUserService extends SysUserServiceImpl {
    public abstract SysUserEntity queryByMobile(String mobile);
    public abstract long login(LoginForm form);
    public abstract String doLogin(LoginForm form);
}
