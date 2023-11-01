/**
 * Copyright (c) 2019-2023 SAAS开源 All rights reserved.
 *
 * SAAS系统设计研发交流
 *
 * https://www.maibaduoduo.com
 */
package com.maibaduoduo.task.event;

import com.maibaduoduo.task.program.ExecuteObject;

import java.io.Serializable;

public class ProgramTask implements Serializable {
    private ExecuteObject executeObject;

    public ExecuteObject getExecuteObject() {
        return executeObject;
    }

    public void setExecuteObject(ExecuteObject executeObject) {
        this.executeObject = executeObject;
    }
}
