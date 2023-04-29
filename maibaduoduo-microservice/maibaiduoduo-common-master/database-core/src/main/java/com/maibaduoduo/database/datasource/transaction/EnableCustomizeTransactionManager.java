/**
 * Copyright (c) 2019-2023 SAAS开源 All rights reserved.
 *
 * SAAS系统设计研发交流
 *
 * https://www.maibaduoduo.com
 */
package com.maibaduoduo.database.datasource.transaction;

import com.maibaduoduo.database.datasource.dynamic.DynamicDataSource;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
/**
 * 自定义事务管理器
 */
@Configuration
@EnableTransactionManagement
@Slf4j
public class EnableCustomizeTransactionManager {

    /**
     * 配置事务管理器
     * @param dynamicDataSource
     * @return
     */
    @Bean(name = "transactionManager")
    public PlatformTransactionManager transactionManager(DynamicDataSource dynamicDataSource) {
        DataSourceTransactionManager transactionManager = new DataSourceTransactionManager();
        transactionManager.setDataSource(dynamicDataSource);
        log.info("PlatformTransactionManager,{}","初始化....");
        return transactionManager;
    }
}
