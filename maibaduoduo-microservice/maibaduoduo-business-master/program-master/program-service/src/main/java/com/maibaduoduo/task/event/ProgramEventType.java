/**
 * Copyright (c) 2019-2023 SAAS开源 All rights reserved.
 *
 * SAAS系统设计研发交流
 *
 * https://www.maibaduoduo.com
 */
package com.maibaduoduo.task.event;

public enum ProgramEventType {
    SAVE(0, "保存"),
    DELETE(1, "删除"),
    EXECUTE(100,"执行"),
    EXECUTE_STATUS(2, "执行状态"),
    EXECUTE_FAIR(3, "执行错误信息");

    private int code;
    private String desc;

    private ProgramEventType(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public int getCode() {
        return this.code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getDesc() {
        return this.desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
