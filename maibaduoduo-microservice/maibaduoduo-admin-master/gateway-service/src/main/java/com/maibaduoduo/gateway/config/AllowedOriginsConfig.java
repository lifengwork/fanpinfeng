package com.maibaduoduo.gateway.config;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import java.util.List;

/**
 * @Desc: 配置Origins
 * @Author:fanpingfeng
 * @Date 2023-4-21
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Component
@ConfigurationProperties(prefix="fanpinfeng.secure.allowed.origins")
public class AllowedOriginsConfig {
    private List<String> origins;
}
