/**
 * Copyright (c) 2019-2023 SAAS开源 All rights reserved.
 *
 * SAAS系统设计研发交流
 *
 * https://www.maibaduoduo.com
 */
package com.maibaduoduo.configuration;


import com.maibaduoduo.configuration.props.Charsets;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;

@Slf4j
public class SaasSysInit implements ApplicationContextInitializer {
    @Override
    public void initialize(ConfigurableApplicationContext configurableApplicationContext) {
         log.info("-SAAS INITING..........");
         if(String.valueOf(Charsets.STT).toUpperCase().equals(configurableApplicationContext.getEnvironment().getProperty(String.valueOf(Charsets.STTDATA)))){
             log.info("-麦BA哆哆，SAAS服务平台，{}","暂不支持SCHEMA模式。");
             System.exit(100);
         }
    }
}
