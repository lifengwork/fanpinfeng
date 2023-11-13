/**
 * Copyright (c) 2019-2023 SAAS开源 All rights reserved.
 * <p>
 * SAAS系统设计研发交流
 * <p>
 * https://www.maibaduoduo.com
 */
package com.maibaduoduo.store.task.aspect;

import com.maibaduoduo.logistics.deliveryman.task.config.EventContants;
import com.maibaduoduo.logistics.deliveryman.task.event.ProgramEvent;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;


/**
 * 防止重复处理切面处理
 */
@Aspect
@Component
public class EventHandlerAspect {
    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Pointcut("@annotation(com.maibaduoduo.logistics.deliveryman.task.aspect.Repetition)")
    public void repetition() {

    }

    @Before("repetition()")
    public void beforeHandle(ProceedingJoinPoint joinPoint) throws Throwable {
        //防止业务逻辑重复执行
        ProgramEvent programEvent = (ProgramEvent) joinPoint.getArgs()[0];
        String keyString = joinPoint.getSignature().getName();
        boolean hasKey = redisTemplate.boundHashOps(EventContants.R_RAWKEY).getOperations().
                hasKey(keyString.toUpperCase() + programEvent.getProgramTask().getExecuteObject().getExecuteId());
        if (!hasKey) {
            joinPoint.proceed();
        }
    }

    @After("repetition()")
    public void afterHandle(ProceedingJoinPoint joinPoint) throws Throwable {
        //防止业务逻辑重复执行
        ProgramEvent programEvent = (ProgramEvent) joinPoint.getArgs()[0];
        String keyString = joinPoint.getSignature().getName();
        redisTemplate.boundHashOps(EventContants.R_RAWKEY).
                put(keyString.toUpperCase() + programEvent.getProgramTask().getExecuteObject().getExecuteId(), "99999");
        redisTemplate.expire(EventContants.R_RAWKEY, 1, TimeUnit.DAYS);
    }
}
