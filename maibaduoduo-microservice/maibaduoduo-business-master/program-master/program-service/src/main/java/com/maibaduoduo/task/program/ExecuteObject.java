/**
 * Copyright (c) 2019-2023 SAAS开源 All rights reserved.
 *
 * SAAS系统设计研发交流
 *
 * https://www.maibaduoduo.com
 */
package com.maibaduoduo.task.program;

import com.maibaduoduo.task.program.strategy.PlanStrategy;
import lombok.Data;

import java.io.Serializable;

@Data
public class ExecuteObject implements Serializable {
    private Long executeId;
    private PlanStrategy planStrategy;
    public ExecuteObject(PlanStrategy planStrategy){
        this.planStrategy = planStrategy;
    }
}
