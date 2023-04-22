package com.maibaduoduo.database.datasource.dynamic;

import cn.hutool.core.exceptions.ExceptionUtil;
import com.alibaba.druid.pool.DruidDataSource;
import com.maibaduoduo.database.datasource.dynamic.properties.DataSourceProperties;
import com.maibaduoduo.database.datasource.dynamic.properties.DynamicDataSourceProperties;
import com.maibaduoduo.database.datasource.dynamic.properties.ServerProperties;
import com.maibaduoduo.database.datasource.utils.BeanUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import redis.clients.jedis.JedisPool;
import java.sql.SQLException;
import java.util.*;

/**
 * 配置多数据源
 * @Date
 * @author admin
 */
@Slf4j
@Configuration
public class DynamicDataSourceConfig {
    @Autowired
    private DynamicDataSourceProperties properties;
    @Autowired
    private JedisPool jedisPool;
    @Autowired
    private ServerProperties serverProperties;
    /**
     * 默认数据源
     *
     * @return
     */
    @Bean("dataSourceProperties")
    @Primary
    @ConfigurationProperties(prefix = "spring.datasource.druid")
    public DataSourceProperties dataSourceProperties() {
        return new DataSourceProperties();
    }

    /**
     * 生成动态数据源bean
     * @param dataSourceProperties
     * @return
     */
    @Bean("dynamicDataSource")
    public DynamicDataSource dynamicDataSource(DataSourceProperties dataSourceProperties) throws SQLException {
        DynamicDataSource dynamicDataSource = new DynamicDataSource();
        //默认数据源
        DruidDataSource druidDataSource = setDataSourceServer(DynamicDataSourceFactory.buildDruidDataSource(dataSourceProperties), dataSourceProperties).get(0);
        dynamicDataSource.setDefaultTargetDataSource(druidDataSource);
        //动态数据源
        dynamicDataSource.setTargetDataSources(getDynamicDataSourceNew());
        return dynamicDataSource;
    }

    /**
     * @return
     */
    private Map<Object, Object> getDynamicDataSourceNew() {
        Set<String> sysTenantDbUrlList =jedisPool.getResource().keys("TENANT_DB_URL_*");
        Map<Object, Object> targetDataSources = new HashMap<>();
        Map<String, DataSourceProperties> dataSourcePropertiesMap = properties.getDatasource();
        try{
            dataSourcePropertiesMap.forEach((k, v) -> {
               try{
                   DruidDataSource druidDataSource =null;
                   for(String dburl:sysTenantDbUrlList){
                       TenantInfo tenantInfo = BeanUtils.stringToBean(jedisPool.getResource().get(dburl),TenantInfo.class);
                       if(k.contains(tenantInfo.getDataType())){
                           druidDataSource = DynamicDataSourceFactory.buildDruidDataSource(v);
                               if(!druidDataSource.isInited()){
                               druidDataSource.setUrl(tenantInfo.getDbUrl());
                               druidDataSource.init();
                               targetDataSources.put(tenantInfo.getDbName(), druidDataSource);
                           }
                       }
                   }
               }catch(SQLException e){
                   log.error(ExceptionUtil.stacktraceToString(e));
               }
            });
        }catch(Exception e){
           log.error(ExceptionUtil.stacktraceToString(e));
        }
        return targetDataSources;
    }


    /**
     * 设置数据源服务器
     *
     * @param druidDataSource
     * @param dataSourceProperties
     * @return
     */
    private List<DruidDataSource> setDataSourceServer(DruidDataSource druidDataSource, DataSourceProperties dataSourceProperties) throws SQLException {
        List<ServerProperties.Server> serverConfigList =serverProperties.getServerList();
        if (serverConfigList.isEmpty()) {
            throw new RuntimeException("Server为空。");
        }
        List<DruidDataSource> druidDataSourceList = new ArrayList<DruidDataSource>();
        for (ServerProperties.Server server : serverConfigList) {
            String Server = String.format("%s:%s", server.getHost(), server.getPort());
            String jdbcUrl = dataSourceProperties.getUrl();
            jdbcUrl = jdbcUrl.replace("[SERVER]", Server);
            if(!druidDataSource.isInited()){
                druidDataSource.setUrl(jdbcUrl);
                druidDataSource.init();
            }
            druidDataSourceList.add(druidDataSource);
            break;
        }
        return druidDataSourceList;
    }
}
