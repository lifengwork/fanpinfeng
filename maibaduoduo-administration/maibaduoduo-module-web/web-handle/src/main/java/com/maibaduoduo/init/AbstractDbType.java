/*
 * Copyright (c) 2019-2023 SAAS开源 All rights reserved.
 *
 * SAAS系统设计研发交流
 *
 * https://www.maibaduoduo.com
 */
package com.maibaduoduo.init;

import com.maibaduoduo.base.tenant.entity.SysTenant;

/**
 * @Description: //TODO
 * @date: 2023/4/18 13:55
 * @Author: pm2022
 */
public abstract class AbstractDbType implements DbType {
    protected String dbPrefixMaster = "datasource_master_";
    protected String dbPrefixSlave = "datasource_slave_";
    protected String sql;
    protected SysTenant sysTenant;
    public DbType buildInfo(String sql,SysTenant sysTenant) {
        this.sql = sql;
        this.sysTenant = sysTenant;
        return this;
    }
}
