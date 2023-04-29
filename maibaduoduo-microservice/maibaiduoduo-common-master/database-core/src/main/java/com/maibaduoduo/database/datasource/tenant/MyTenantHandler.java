/**
 * Copyright (c) 2019-2023 SAAS开源 All rights reserved.
 *
 * SAAS系统设计研发交流
 *
 * https://www.maibaduoduo.com
 */
package com.maibaduoduo.database.datasource.tenant;

import com.baomidou.mybatisplus.extension.plugins.tenant.TenantHandler;
/**
 * @Description: //TODO
 * @date: 2023/4/19 16:35
 * @Author: pm2022
 */
public interface MyTenantHandler extends TenantHandler {
    boolean doAdminFilter();
}
