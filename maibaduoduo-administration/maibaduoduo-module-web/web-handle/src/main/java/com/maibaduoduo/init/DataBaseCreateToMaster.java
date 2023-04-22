/*
 * Copyright (c) 2019-2023 SAAS开源 All rights reserved.
 *
 * SAAS系统设计研发交流
 *
 * https://www.maibaduoduo.com
 */
package com.maibaduoduo.init;

import com.alibaba.druid.pool.DruidDataSource;
import com.google.common.collect.Lists;
import com.maibaduoduo.base.db.DbMapper;
import com.maibaduoduo.common.datasources.DataSourceContextHolder;
import com.maibaduoduo.common.datasources.DynamicDataSourceInit;
import com.maibaduoduo.common.datasources.Server;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.SQLException;
import java.util.List;

/**
 * @Description: //TODO
 * @date: 2023/4/18 13:49
 * @Author: pm2022
 */
@Component
public class DataBaseCreateToMaster extends AbstractDbType {

    @Autowired
    private DbMapper dbMapper;

    @Override
    public void excute() throws SQLException {
        try{
            DataSourceContextHolder.setDB("commonmaster");
            String dbName = dbPrefixMaster.concat(sysTenant.getId());//数据库的名称
            String sqldb = "CREATE DATABASE IF NOT EXISTS " + dbName + " CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;";
            //建库
            dbMapper.exec(sqldb);
            //建表
            String useSql = "use " + dbName + ";\r\n";
            dbMapper.exec(useSql + sql);
        }catch (Exception e){
           throw new SQLException(e);
        }
    }
}
