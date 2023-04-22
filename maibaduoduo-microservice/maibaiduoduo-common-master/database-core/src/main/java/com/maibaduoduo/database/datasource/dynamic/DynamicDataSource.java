package com.maibaduoduo.database.datasource.dynamic;

import cn.hutool.core.exceptions.ExceptionUtil;
import com.alibaba.druid.pool.DruidDataSource;
import com.maibaduoduo.database.datasource.SpringContextUtils;
import com.maibaduoduo.database.datasource.TenantDataSource;
import com.maibaduoduo.database.datasource.dynamic.properties.DataSourceProperties;
import com.maibaduoduo.database.datasource.utils.DynamicDataSourceContextHolder;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;
import java.util.HashMap;
import java.util.Map;
/**
 * 动态数据源实现类
 */
@Slf4j
public class DynamicDataSource extends AbstractRoutingDataSource {
    private static Logger logger = LoggerFactory.getLogger(DynamicDataSource.class);
    /**
     *动态选择当前要执行的数据源，如果为空执行默认的数据源
     **/
    @Override
    protected Object determineCurrentLookupKey() {
        String currentDs = null;
        try {
            currentDs = TenantDataSource.instanceSingletonObject().getCurrentTenantDatasource();
        } catch (Exception e) {
            logger.error(ExceptionUtil.stacktraceToString(e));
        }
        if (StringUtils.isEmpty(currentDs)) {
            log.info("默认数据源，{}", "default");
        } else {
            log.info("当前数据源，{}", currentDs);
        }
        return currentDs;
    }
    /**
     * 设置数据源
     *
     * @param dataSources
     */
    public void setDataSources(Map<Object, Object> dataSources) {
        super.setTargetDataSources(dataSources);
        this.afterPropertiesSet();
        DynamicDataSourceContextHolder.addDataSourceKeys(dataSources.keySet());
    }

    /**
     * 初始化租户独立数据源
     * @param key
     * @param value
     */
    public void addTenantTargetDataSources(String key,String value){
        try{
            Map<Object,Object> addTenantDataSource = new HashMap<Object,Object>();

            DataSourceProperties dataSourceProperties = (DataSourceProperties)SpringContextUtils.getBean("dataSourceProperties");
            DruidDataSource druidDataSource =DynamicDataSourceFactory.buildDruidDataSource(dataSourceProperties);
            if(!druidDataSource.isInited()){
                druidDataSource.setUrl(value);
                druidDataSource.init();
                addTenantDataSource.put(key, druidDataSource);
            }
            this.setDataSources(addTenantDataSource);
        }catch(Exception e){
            logger.error(ExceptionUtil.stacktraceToOneLineString(e));
        }

    }
}
