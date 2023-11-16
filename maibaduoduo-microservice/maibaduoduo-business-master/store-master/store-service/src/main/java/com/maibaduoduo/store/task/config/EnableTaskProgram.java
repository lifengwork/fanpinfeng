/*
 * Copyright (c) 2019-2023 SAAS开源 All rights reserved. lifengwork@yeah.net
 * SAAS系统设计研发交流
 * https://www.maibaduoduo.com
 */
package com.maibaduoduo.store.task.config;

import com.maibaduoduo.task.config.ProgramConfig;
import com.maibaduoduo.task.config.ProgramProperties;
import com.maibaduoduo.task.program.Program;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@EnableConfigurationProperties({ProgramProperties.class})
@Component
public class EnableTaskProgram {
    private Logger logger = LoggerFactory.getLogger(EnableTaskProgram.class);

    @Bean
    @Scope("singleton")
    public ProgramConfig programConfig(Program program, ProgramProperties programProperties){
        this.shutdownHook("StockUpConfigHandler");
        ProgramConfig programConfig = new StockUpConfigHandler(program);
        programConfig.start(programProperties.getBufferSize());
        return programConfig;
    }

    @Bean
    @Scope("singleton")
    public ProgramConfig purchaseProgramConfig(Program program, ProgramProperties programProperties){
        this.shutdownHook("PurchaseConfigHandler");
        ProgramConfig purchaseConfigHandler = new PurchaseConfigHandler(program);
        purchaseConfigHandler.start(programProperties.getBufferSize());
        return purchaseConfigHandler;
    }

    private void shutdownHook(String confighandler){
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            logger.error(confighandler + "have error!");
        }));
    }
}
