package com.maibaduoduo.database.datasource.dynamic;

public class TenantType extends TenantInfo {
    public String getTenantType() {
        if(this.getEmployee().equals(this.getTenantId())){
            return this.getTenantId();
        }
        return this.getEmployee();
    }
}
