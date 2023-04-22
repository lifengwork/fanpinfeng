package com.maibaduoduo.common.event;

public class TenantEvent extends Event {
    protected Integer status;

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public TenantEvent(Object source) {
        super(source);
    }
}
