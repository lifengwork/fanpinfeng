/**
 * Copyright (c) 2019-2023 SAAS开源 All rights reserved.
 *
 * SAAS系统设计研发交流
 *
 * https://www.maibaduoduo.com
 */
package com.maibaduoduo.task.program;

import org.springframework.stereotype.Component;

/**
 * 计划排程
 * @Auth saas
 * @Date 2021-5-7
 */
@Component
public class SchedulingProgram implements Program{
    @Override
    public boolean execute(ExecuteObject executeObject) {
        return false;
    }
}
