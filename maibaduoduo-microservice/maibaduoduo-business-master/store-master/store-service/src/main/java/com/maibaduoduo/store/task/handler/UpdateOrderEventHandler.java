/*
 * Copyright (c) 2019-2023 SAAS开源 All rights reserved. lifengwork@yeah.net
 * SAAS系统设计研发交流
 * https://www.maibaduoduo.com
 */
package com.maibaduoduo.store.task.handler;

import com.maibaduoduo.store.task.aspect.Repetition;
import com.maibaduoduo.store.task.event.ProgramEventType;
import com.maibaduoduo.store.task.event.ProgramTask;
import com.maibaduoduo.store.task.event.PurchaseProgramEvent;
import com.maibaduoduo.store.task.handler.base.PurchaseMainEventHandler;
import com.maibaduoduo.store.task.program.Program;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.concurrent.Executor;

/**
 * 订单备货
 * @author saas
 */
@Component
public class UpdateOrderEventHandler extends PurchaseMainEventHandler {
    private Program program;
    private Executor executor;
    @Autowired
    private RedisTemplate<String, Object> redisTemplate;
    /**
     * 更新订单状态（已发货）
     * @param programEvent
     */
    @Override
    @Repetition
    public void doHandle(final PurchaseProgramEvent programEvent) {
        executor.execute(() -> {
            if (programEvent.getType() == ProgramEventType.EXECUTE.getCode()) {
                logger.info("Event Type is EXECUTE，{}",programEvent.getType());
                ProgramTask programTask = programEvent.getProgramTask();
                program.execute(programTask.getExecuteObject());
            }
            programEvent.clear();
        });
    }

    @Override
    public PurchaseMainEventHandler programEventHandlerInit(Program program, Executor executor) {
        this.program = program;
        this.executor = executor;
        return this;
    }

    @Override
    public void beforeExecute(PurchaseProgramEvent programEvent) {

    }

    @Override
    public void afterExecute(PurchaseProgramEvent programEvent) {

    }
}
