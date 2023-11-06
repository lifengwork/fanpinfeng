/*
 * Copyright (c) 2019-2023 SAAS开源 All rights reserved. lifengwork@yeah.net
 * SAAS系统设计研发交流
 * https://www.maibaduoduo.com
 */
package com.maibaduoduo.task.event;

import com.maibaduoduo.task.program.ExecuteObject;

import java.io.Serializable;

public class ProgramTask implements Serializable {
    private String taskCode;
    private String taskName;
    private ExecuteObject executeObject;

    public ExecuteObject getExecuteObject() {
        return executeObject;
    }

    public void setExecuteObject(ExecuteObject executeObject) {
        this.executeObject = executeObject;
    }

    public String getTaskCode() {
        return taskCode;
    }

    public void setTaskCode(String taskCode) {
        this.taskCode = taskCode;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }
}
