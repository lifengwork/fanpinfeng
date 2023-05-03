package com.maibaduoduo.gateway.config;

import cn.hutool.core.util.ObjectUtil;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.reactive.CorsConfigurationSource;
import org.springframework.web.cors.reactive.CorsWebFilter;

import java.util.Collections;
import java.util.Objects;

/**
 * @Desc: 网关跨域设置
 * @Author:fanpingfeng
 * @Date 2023-4-21
 */
@Configuration
public class CorsConfig {

    /**
     *
     * @param allowedOriginsConfig
     * @param allowedHeadsConfig
     * @param allowedMethodsConfig
     * @return
     */
    @Bean
    public CorsWebFilter corsWebFilter(AllowedOriginsConfig allowedOriginsConfig, AllowedHeadsConfig allowedHeadsConfig, AllowedMethodsConfig allowedMethodsConfig) {
        CorsConfigurationSource corsConfigurationSource = serverWebExchange -> {
            CorsConfiguration cors = new CorsConfiguration();
            if (Objects.nonNull(allowedHeadsConfig.getHeads()) && !allowedHeadsConfig.getHeads().isEmpty()) {
                allowedHeadsConfig.getHeads().stream().forEach(head -> cors.addAllowedHeader(head));
            } else {
                cors.addAllowedHeader(CorsConfiguration.ALL);
            }
            if (Objects.nonNull(allowedMethodsConfig.getMethods()) && !allowedMethodsConfig.getMethods().isEmpty()) {
                allowedMethodsConfig.getMethods().stream().forEach(method -> cors.addAllowedMethod(method));
            } else {
                cors.addAllowedMethod(CorsConfiguration.ALL);
            }
            cors.setAllowedOrigins(ObjectUtil.isNotNull(allowedOriginsConfig.getOrigins()) ?
                    allowedOriginsConfig.getOrigins() : Collections.singletonList("*"));
            cors.setAllowCredentials(true);
            return cors;
        };

        return new CorsWebFilter(corsConfigurationSource);
    }

}