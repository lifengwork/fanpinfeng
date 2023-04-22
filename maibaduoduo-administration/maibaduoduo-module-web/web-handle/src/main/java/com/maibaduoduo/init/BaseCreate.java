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
import com.maibaduoduo.base.tenant.entity.SysTenant;
import com.maibaduoduo.common.datasources.DynamicDataSourceInit;
import com.maibaduoduo.common.datasources.Server;
import org.apache.avalon.framework.ExceptionUtil;
import org.apache.log4j.Logger;
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
public class BaseCreate extends AbstractDataBaseCreate {
    private Logger log = Logger.getLogger(BaseCreate.class);
    @Autowired
    private DataBaseCreateToMaster dataBaseCreateToMaster;
    @Autowired
    private DataBaseCreateToSlave dataBaseCreateToSlave;

    @Override
    public void create(SysTenant sysTenant) {
        List<DruidDataSource> sources = null;
        try {
            List<Server> serversMasterSlave = Lists.newArrayList(
                    new Server(sysTenant.getMysqlServerIp(), sysTenant.getMysqlPort()),
                    new Server(sysTenant.getSlaveServerIp(), sysTenant.getSlaveServerPort()));
            sources = DynamicDataSourceInit.setDataSourceServer(serversMasterSlave, true);
            switch (sysTenant.getIsMasterSlave()) {
                case 1:
                    this.initDb(dataBaseCreateToMaster.buildInfo(this.buildSql(), sysTenant));
                    this.initDb(dataBaseCreateToSlave.buildInfo(this.buildSql(), sysTenant));
                    break;
                default:
                    this.initDb(dataBaseCreateToMaster.buildInfo(this.buildSql(), sysTenant));
            }
        } catch (Exception e) {
            log.error(ExceptionUtil.printStackTrace(e));
        } finally {
            String masterHost = sysTenant.getMysqlServerIp() + sysTenant.getMysqlPort();
            String slaveHost = sysTenant.getSlaveServerIp() + sysTenant.getSlaveServerPort();
            //如果主从数据源一样关闭一个即可否则错误。
            if (masterHost.equals(slaveHost)) {
                DruidDataSource druidDataSource = sources.get(0);
                if (druidDataSource.isEnable()) {
                    druidDataSource.close();
                }
            } else {
                if (!sources.isEmpty()) {
                    sources.stream().forEach(data -> {
                        if (data.isEnable()) {
                            data.close();
                        }
                    });
                }
            }
        }
    }

    @Override
    protected void initDb(DbType dbType) throws SQLException {
        dbType.excute();
    }
}
