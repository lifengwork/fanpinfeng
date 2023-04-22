package com.maibaduoduo.database.datasource.dynamic;

public class TenantInfo {
    private String tenantId;		// tenant_id
    private String employee;		// employee
    private String dbUrl;		// db_url
    private String dbName;
    private String dataType;

    public TenantInfo setTenantInfo(String tenantId, String employee, String dbUrl, String dbName, String dataType){
        this.dbUrl=dbUrl;
        this.tenantId=tenantId;
        this.employee=employee;
        this.dbName=dbName;
        this.dataType=dataType;
        return this;
    }

    public String getTenantId() {
        return tenantId;
    }

    public void setTenantId(String tenantId) {
        this.tenantId = tenantId;
    }

    public String getEmployee() {
        return employee;
    }

    public void setEmployee(String employee) {
        this.employee = employee;
    }

    public String getDbUrl() {
        return dbUrl;
    }

    public void setDbUrl(String dbUrl) {
        this.dbUrl = dbUrl;
    }

    public String getDbName() {
        return dbName;
    }

    public void setDbName(String dbName) {
        this.dbName = dbName;
    }

    public String getDataType() {
        return dataType;
    }

    public void setDataType(String dataType) {
        this.dataType = dataType;
    }
}
