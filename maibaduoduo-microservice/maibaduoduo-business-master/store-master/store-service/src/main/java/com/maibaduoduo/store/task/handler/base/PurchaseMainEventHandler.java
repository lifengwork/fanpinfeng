/*
 * Copyright (c) 2019-2023 SAAS开源 All rights reserved. lifengwork@yeah.net
 * SAAS系统设计研发交流
 * https://www.maibaduoduo.com
 */
package com.maibaduoduo.store.task.handler.base;

import com.lmax.disruptor.WorkHandler;
import com.maibaduoduo.configuration.exception.SaasException;
import com.maibaduoduo.store.task.event.ProgramEvent;
import com.maibaduoduo.store.task.event.PurchaseProgramEvent;
import com.maibaduoduo.store.task.program.Program;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.Executor;

public abstract class PurchaseMainEventHandler implements WorkHandler<PurchaseProgramEvent> {
    protected Logger logger = LoggerFactory.getLogger(getClass());
    private Long HANDLERCODE = null;
    public void doHandle(PurchaseProgramEvent programEvent){
        throw new SaasException("任务处理器为空，请实现。");
    }

    public abstract PurchaseMainEventHandler programEventHandlerInit(Program program, Executor executor);

    @Override
    public void onEvent(PurchaseProgramEvent programEvent){
        this.beforeExecute(programEvent);
        this.doHandle(programEvent);
        this.afterExecute(programEvent);
    }
    public abstract void beforeExecute(PurchaseProgramEvent programEvent);
    public abstract void afterExecute(PurchaseProgramEvent programEvent);
}
