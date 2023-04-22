package com.maibaduoduo.common.datasources;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

@Order(2)
public class DynamicDataSource extends AbstractRoutingDataSource {
    private static Logger log = LoggerFactory.getLogger(DynamicDataSource.class);
    @Override
    protected Object determineCurrentLookupKey() {
        log.info("当前数据源为,{}",DataSourceContextHolder.getDB());
        return DataSourceContextHolder.getDB();
    }

}