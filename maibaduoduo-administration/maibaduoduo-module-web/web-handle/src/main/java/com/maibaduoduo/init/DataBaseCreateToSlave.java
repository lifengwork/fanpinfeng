/*
 * Copyright (c) 2019-2023 SAAS开源 All rights reserved.
 *
 * SAAS系统设计研发交流
 *
 * https://www.maibaduoduo.com
 */
package com.maibaduoduo.init;

import com.maibaduoduo.base.db.DbMapper;
import com.maibaduoduo.common.datasources.DataSourceContextHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.SQLException;

/**
 * @Description: //TODO
 * @date: 2023/4/18 13:49
 * @Author: pm2022
 */
@Component
public class DataBaseCreateToSlave extends AbstractDbType {

    @Autowired
    private DbMapper dbMapper;

    @Override
    public void excute() throws SQLException {
        try{
            DataSourceContextHolder.setDB("commonslave");
            String dbName = dbPrefixSlave.concat(sysTenant.getId());//.concat("_slave");
            String sqldb = "CREATE DATABASE IF NOT EXISTS " + dbName + " CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;";
            dbMapper.exec(sqldb);
            //建表
            String useSql = "use " + dbName + ";\r\n";
            dbMapper.exec(useSql + sql);
        }catch (Exception e){
            throw new SQLException(e);
        }
    }
}
