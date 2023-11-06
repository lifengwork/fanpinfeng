/*
 * Copyright (c) 2019-2023 SAAS开源 All rights reserved. lifengwork@yeah.net
 * SAAS系统设计研发交流
 * https://www.maibaduoduo.com
 */
package com.maibaduoduo.task.program;

import com.maibaduoduo.task.program.strategy.Strategy;
import org.springframework.stereotype.Component;

/**
 * 执行
 * @Auth saas
 * @Date 2021-5-7
 */
@Component
public class SchedulingProgram implements Program{
    @Override
    public boolean execute(ExecuteObject executeObject) {
        executeObject.getExecuteId();
        executeObject.getPlanStrategy().strategy(new Strategy(){});
        return false;
    }
}
