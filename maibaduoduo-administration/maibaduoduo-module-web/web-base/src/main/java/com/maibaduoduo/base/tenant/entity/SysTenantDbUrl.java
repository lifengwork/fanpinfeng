package com.maibaduoduo.base.tenant.entity;

import com.maibaduoduo.common.entity.DataEntity;
import com.maibaduoduo.common.utils.StringUtils;
import org.codehaus.groovy.util.StringUtil;
import org.hibernate.validator.constraints.Length;

/**
 * 单表生成Entity
 * @author admin
 * @version 2023-04-08
 */
public class SysTenantDbUrl extends DataEntity<SysTenantDbUrl> {
	
	private static final long serialVersionUID = 1L;
	private String tenantId;		// tenant_id
	private String employee;		// employee
	private String dbUrl;		// db_url
	private String initData; //初始化数据源

	public String getInitData() {
		if(this.getTenantId().equals(this.getEmployee())){
			this.initData="1"; //默认租户初始化数据源
		}
		return initData;
	}

	public void setInitData(String initData) {
		this.initData = initData;
	}

	public SysTenantDbUrl() {
		super();
	}

	public SysTenantDbUrl(String id){
		super(id);
	}

	@Length(min=1, max=32, message="tenant_id长度必须介于 1 和 32 之间")
	public String getTenantId() {
		return tenantId;
	}

	public void setTenantId(String tenantId) {
		this.tenantId = tenantId;
	}
	
	@Length(min=0, max=255, message="employee长度必须介于 0 和 255 之间")
	public String getEmployee() {
		return employee;
	}

	public void setEmployee(String employee) {
		this.employee = employee;
	}
	
	@Length(min=0, max=255, message="db_url长度必须介于 0 和 255 之间")
	public String getDbUrl() {
		return dbUrl;
	}

	public void setDbUrl(String dbUrl) {
		this.dbUrl = dbUrl;
	}

}