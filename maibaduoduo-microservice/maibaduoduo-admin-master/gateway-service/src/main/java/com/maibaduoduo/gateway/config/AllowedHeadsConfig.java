package com.maibaduoduo.gateway.config;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @Desc: 配置Heads
 * @Author: fanpingfeng
 * @Date 2023-4-21
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Component
@ConfigurationProperties(prefix="fanpinfeng.secure.allowed.heads")
public class AllowedHeadsConfig {
    private List<String> heads;
}
