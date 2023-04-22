/*
 * Copyright (c) 2019-2023 SAAS开源 All rights reserved.
 *
 * SAAS系统设计研发交流
 *
 * https://www.maibaduoduo.com
 */
package com.maibaduoduo.init;


import com.maibaduoduo.base.tenant.entity.SysTenant;

import java.sql.SQLException;

/**
 * @Description: //TODO
 * @date: 2023/4/18 13:45
 * @Author: pm2022
 */
public interface DbType {
    DbType buildInfo(String sql,SysTenant sysTenant);
    void excute() throws SQLException;
}
