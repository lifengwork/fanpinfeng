package com.maibaduoduo.common.event;

public class TenantInitDataSourceInfo extends TenantInitDataSourceEvent {
    private String dbKey;
    private String dbUrl;

    public String getDbKey() {
        return dbKey;
    }

    public void setDbKey(String dbKey) {
        this.dbKey = dbKey;
    }

    public String getDbUrl() {
        return dbUrl;
    }

    public void setDbUrl(String dbUrl) {
        this.dbUrl = dbUrl;
    }

    public TenantInitDataSourceInfo(Object source) {
        super(source);
    }
}
