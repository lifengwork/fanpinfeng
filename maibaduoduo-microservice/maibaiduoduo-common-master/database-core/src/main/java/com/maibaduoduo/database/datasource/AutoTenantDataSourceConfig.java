package com.maibaduoduo.database.datasource;

import com.maibaduoduo.database.datasource.dynamic.properties.LoginProperties;
import com.maibaduoduo.database.datasource.dynamic.properties.ServerProperties;
import com.maibaduoduo.jwt.TokenUtil;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.Scope;
import redis.clients.jedis.JedisPool;

@EnableConfigurationProperties(value = {LoginProperties.class,
        ServerProperties.class
})
@PropertySource(encoding = "UTF-8", value = {"classpath:ddsc.yml"})
public class AutoTenantDataSourceConfig {
    @Bean
    @Scope("singleton")
    public TenantDataSource tenantDataSource(JedisPool jedisPool, TokenUtil tokenUtil, LoginProperties loginProperties){
        return  new TenantDataSource(jedisPool,tokenUtil,loginProperties);
    }
}
