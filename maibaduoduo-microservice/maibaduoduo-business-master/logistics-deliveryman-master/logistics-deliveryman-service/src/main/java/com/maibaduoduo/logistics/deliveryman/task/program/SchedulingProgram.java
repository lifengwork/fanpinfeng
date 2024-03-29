/*
 * Copyright (c) 2019-2023 SAAS开源 All rights reserved. lifengwork@yeah.net
 * SAAS系统设计研发交流
 * https://www.maibaduoduo.com
 */
package com.maibaduoduo.logistics.deliveryman.task.program;

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
        /**
         * TODO 更新订单状态
         * 首先判断此加急配送订单是否已经被处理
         */
        return false;
    }
}
