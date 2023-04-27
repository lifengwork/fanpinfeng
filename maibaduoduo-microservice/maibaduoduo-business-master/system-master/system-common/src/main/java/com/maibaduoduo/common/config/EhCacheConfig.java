package com.maibaduoduo.common.config;

import net.sf.ehcache.CacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class EhCacheConfig {
    @Bean("ehcacheManager")
    public CacheManager cacheManager(){
        return  CacheManager.getInstance();
    }
}
