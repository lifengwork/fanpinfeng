package com.maibaduoduo.common.event;

import java.io.Serializable;

public class TenantAuthorizationInfo extends TenantAuthorizationEvent implements Serializable {
    private String tenantId;
    private String employeeId;
    public TenantAuthorizationInfo(Object source) {
        super(source);
    }

    public String getTenantId() {
        return tenantId;
    }

    public void setTenantId(String tenantId) {
        this.tenantId = tenantId;
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }
}
