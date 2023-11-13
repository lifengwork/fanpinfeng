/*
 * Copyright (c) 2019-2023 SAAS开源 All rights reserved. lifengwork@yeah.net
 * SAAS系统设计研发交流
 * https://www.maibaduoduo.com
 */
package com.maibaduoduo.store.task.config;

import com.maibaduoduo.store.task.program.Program;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@EnableConfigurationProperties({ProgramProperties.class})
@Component
public class EnableStoreProgram {

    @Bean
    @Scope("singleton")
    public ProgramConfig programConfig(Program program, ProgramProperties programProperties){
        ProgramConfig programConfig = new StockUpConfigHandler(program);
        programConfig.start(programProperties.getBufferSize());
        return programConfig;
    }

    @Bean
    @Scope("singleton")
    public PurchaseProgramConfig purchaseProgramConfig(Program program, ProgramProperties programProperties){
        PurchaseProgramConfig purchaseConfigHandler = new PurchaseConfigHandler(program);
        purchaseConfigHandler.start(programProperties.getBufferSize());
        return purchaseConfigHandler;
    }
}
