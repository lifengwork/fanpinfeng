package com.maibaduoduo.gateway.dyroute;

import com.alibaba.cloud.nacos.NacosConfigManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

/**
 * @Desc: 动态路由//TODO
 * @Author: fanpingfeng
 * @Date 2023-4-21
 */
@Component
@ConfigurationProperties(prefix = "maiba.gateway.dyroute")
@ConditionalOnProperty(prefix = "maiba.gateway.dyroute", name = "enable", havingValue = "true")
public class DyRouteConfig {
    @Autowired
    private ApplicationEventPublisher applicationEventPublisher;
    @Configuration
    @ConditionalOnProperty(prefix = "maiba.gateway.dyroute", name = "dyType", havingValue = "nacos", matchIfMissing = false)
    public class NacosDynamicRoute {

        @Autowired
        private NacosConfigManager nacosConfigManager;

        @Value("${maiba.gateway.dyroute.dy_group}")
        private String dyGroup;
        @Bean
        public NacosRouteDefinitionRepository nacosRouteDefinitionRepository() {
            return new NacosRouteDefinitionRepository(applicationEventPublisher, nacosConfigManager,dyGroup);
        }
    }
}
