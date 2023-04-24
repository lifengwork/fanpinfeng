package com.maibaduoduo.database.datasource;
import com.maibaduoduo.database.datasource.dynamic.properties.LoginProperties;
import com.maibaduoduo.database.datasource.dynamic.properties.ServerProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * @Description: //TODO
 * @date: 2023/4/17 17:46
 * @Author: pm2022
 */
@Configuration
@PropertySource(encoding = "UTF-8", value = {"classpath:ddsc.yml"})
public class TenantConfig {
    @Bean("loginProperties")
    @ConfigurationProperties(prefix = "maibaduoduo.login")
    public LoginProperties loginProperties() {
        return new LoginProperties();
    }

    @Bean("serverProperties")
    @ConfigurationProperties(prefix = "maibaduoduo.server")
    public ServerProperties serverProperties() {
        return new ServerProperties();
    }

}
