package com.maibaduoduo.base.tenant.entity;

import com.maibaduoduo.common.entity.DataEntity;
import org.hibernate.validator.constraints.Length;

/**
 * 开通登录权限Entity
 * @author admin
 * @version 2023-04-11
 */
public class TenantEmployeeInfo extends DataEntity<TenantEmployeeInfo> {
	
	private static final long serialVersionUID = 1L;
	private String tenantId;		// 用户编码
	private String tenantEmployeeId;		// 员工编码
	private String tenantName;		// 租户名称
	private String tenantEmployeeName;		// 员工名称
	private Integer status;		// 开通状态
	
	public TenantEmployeeInfo() {
		super();
	}

	public TenantEmployeeInfo(String id){
		super(id);
	}

	@Length(min=0, max=32, message="用户编码长度必须介于 0 和 32 之间")
	public String getTenantId() {
		return tenantId;
	}

	public void setTenantId(String tenantId) {
		this.tenantId = tenantId;
	}
	
	@Length(min=1, max=32, message="员工编码长度必须介于 1 和 32 之间")
	public String getTenantEmployeeId() {
		return tenantEmployeeId;
	}

	public void setTenantEmployeeId(String tenantEmployeeId) {
		this.tenantEmployeeId = tenantEmployeeId;
	}
	
	@Length(min=0, max=32, message="租户名称长度必须介于 0 和 32 之间")
	public String getTenantName() {
		return tenantName;
	}

	public void setTenantName(String tenantName) {
		this.tenantName = tenantName;
	}
	
	@Length(min=0, max=32, message="员工名称长度必须介于 0 和 32 之间")
	public String getTenantEmployeeName() {
		return tenantEmployeeName;
	}

	public void setTenantEmployeeName(String tenantEmployeeName) {
		this.tenantEmployeeName = tenantEmployeeName;
	}
	
	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}
	
}