package com.maibaduoduo.gateway.config;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.assertj.core.util.Lists;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @Desc: 自定义过滤请求//TODO
 * @Author: fanpingfeng
 * @Date 2023-4-21
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Component
@ConfigurationProperties(prefix="secure.ignore")
public class IgnoreUrlsConfig {
    private static final String URI_PRE="/api/";
    public static List<String> urls= Lists.newArrayList(
            URI_PRE+"app/login",
            URI_PRE+"sys/login",
            URI_PRE+"sys/captcha.jpg",
            "/webjars/**",
            "/druid/**",
            "/druid/sql.html",
            "/mqtt/**",
            "/instances/**",
            "/actuator/**",
            "/api/order/ribbon"
    );
}
