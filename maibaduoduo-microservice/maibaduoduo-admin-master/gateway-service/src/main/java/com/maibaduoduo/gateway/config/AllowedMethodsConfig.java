package com.maibaduoduo.gateway.config;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @Desc: 配置Methods
 * @Author:fanpingfeng
 * @Date 2023-4-21
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Component
@ConfigurationProperties(prefix="fanpinfeng.secure.allowed.methods")
public class AllowedMethodsConfig {
    private List<String> methods;
}
