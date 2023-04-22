package com.maibaduoduo.common.datasources;

import com.alibaba.druid.pool.DruidDataSource;
import com.google.common.collect.Maps;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 设置数据源服务器
 * @Auth lf
 * @Date 2021-09-02
 */
@Configuration
public class DynamicDataSourceInit {
    private static Map<Object, Object> targetDataSources = Maps.newHashMap();
    /**
     * 生成动态数据源bean
     *
     * @param
     * @return
     */
    @Bean("dynamicDataSource")
    public DynamicDataSource dynamicDataSource() throws SQLException {
        DynamicDataSource dynamicDataSource = new DynamicDataSource();
        //动态数据源
        dynamicDataSource.setTargetDataSources(targetDataSources);
        return dynamicDataSource;
    }

    /**
     *
     * @param servers
     * @return
     * @throws SQLException
     */
    public static List<DruidDataSource> setDataSourceServer(List<Server> servers,boolean isMasterSlave) throws SQLException {
        List<DruidDataSource> druidDataSources = DataSources.sources;

        if(isMasterSlave){
            for(int index=0;index<druidDataSources.size();index++){
                String Server = servers.get(index).getIp().concat(":").concat(servers.get(index).getPort());
                String jdbcUrl = druidDataSources.get(index).getUrl();
                jdbcUrl = jdbcUrl.replace("[SERVER]", Server);
                if(!druidDataSources.get(index).isInited()){
                    druidDataSources.get(index).setUrl(jdbcUrl);
                    druidDataSources.get(index).init();
                    if(0==index){
                        targetDataSources.put("commonmaster",druidDataSources);
                    }else{
                        targetDataSources.put("commonslave",druidDataSources);
                    }
                }
            }
        }else{
            String Server = servers.get(0).getIp().concat(":").concat(servers.get(0).getPort());
            String jdbcUrl = druidDataSources.get(0).getUrl();
            jdbcUrl = jdbcUrl.replace("[SERVER]", Server);
            if(!druidDataSources.get(0).isInited()){
                druidDataSources.get(0).setUrl(jdbcUrl);
                druidDataSources.get(0).init();
                targetDataSources.put("commonmaster",druidDataSources);
            }
        }
        return druidDataSources;
    }
}