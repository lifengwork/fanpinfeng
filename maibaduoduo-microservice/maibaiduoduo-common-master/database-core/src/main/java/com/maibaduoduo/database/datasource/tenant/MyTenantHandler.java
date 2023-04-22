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
