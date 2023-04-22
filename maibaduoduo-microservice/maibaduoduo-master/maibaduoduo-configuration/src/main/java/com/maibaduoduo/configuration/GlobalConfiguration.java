/**
 * Copyright (c) 2019-2023 SAAS开源 All rights reserved.
 *
 * SAAS系统设计研发交流
 *
 * https://www.maibaduoduo.com
 */
package com.maibaduoduo.configuration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import java.time.LocalDate;


/**
 * 所有配置的入口
 */
@Configuration
public class GlobalConfiguration {
    private Logger logger = LoggerFactory.getLogger(getClass());
    @PostConstruct
    private void init(){
        logger.info("系统初始化配置完成..{}", LocalDate.now());
    }

}
