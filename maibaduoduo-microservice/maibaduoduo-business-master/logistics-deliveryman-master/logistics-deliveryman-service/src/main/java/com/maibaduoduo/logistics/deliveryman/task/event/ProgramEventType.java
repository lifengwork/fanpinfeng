/*
 * Copyright (c) 2019-2023 SAAS开源 All rights reserved. lifengwork@yeah.net
 * SAAS系统设计研发交流
 * https://www.maibaduoduo.com
 */
package com.maibaduoduo.logistics.deliveryman.task.event;

public enum ProgramEventType {
    SAVE(0, "保存"),
    DELETE(1, "删除"),
    EXECUTE(100,"执行"),
    EXECUTE_STATUS(2, "执行状态"),
    EXECUTE_FAIR(3, "执行错误信息"),
    EXECUTE_SET(1000, "工序参数设置"),
    EXECUTE_START(1001, "开始生产"),
    EXECUTE_END(1002, "结束生产"),
    EXECUTE_RUSHORDER_STATUS(1003, "更新加急订单状态"),
    EXECUTE_RUSHORDER_SETTLEMENT(1004, "加急订单金额结算"),
    EXECUTE_RUSHORDER_SETCACHE(1005, "加急订单数据放入缓存");
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
