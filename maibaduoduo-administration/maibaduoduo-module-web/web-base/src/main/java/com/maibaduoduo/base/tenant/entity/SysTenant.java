package com.maibaduoduo.base.tenant.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.maibaduoduo.common.entity.DataEntity;
import org.hibernate.validator.constraints.Length;

import java.util.Date;

/**
 * 租户信息Entity
 * @author maibaduoduo
 * @version 2021-09-01
 */
public class SysTenant extends DataEntity<SysTenant> {
	
	private static final long serialVersionUID = 1L;
	private String orgCode;		// 机构编码
	private String orgName;		// 机构名称
	private String logo;		// logo地址
	private String enterpriseDescribe;		// 企业简介
	private String enterpriseDomain;		// 域名
	private String duty;		// 责任人
	private Date expirationTime;		// 有效期为空表示永久
	private String passwordExpire;		// 用户密码有效期单位：天 0表示永久有效
	private Integer isMultipleLogin;		// 是否多地登录
	private String passwordErrorNum;		// 密码输错次数密码输错锁定账号的次数单位：次
	private String passwordErrorLockTime;		// 账号锁定时间密码输错${passwordErrorNum}次后，锁定账号的时间单位： h | d | w | m单位： 时 | 天 | 周 | 月如：0=当天晚上24点 2h = 2小时   2d = 2天
	private String status;		// 租户状态
	private String tenantName;		// 租户名称
	private String tenantPassword;		// 租户密码
	private String tenantPhone;		// 租户手机
	private String mysqlServerIp;		// 数据库IP
	private String mysqlPort;		// 数据库端口
	private String mysqlPassword;		// 数据库密码
	private String msyqlUserName;		// 数据库用户名
	private String mysqlDbName;		   // 数据库名称
	private Integer isMasterSlave=1;		// 开启主从
	private String slaveServerIp;		// 从库服务器
	private String slaveServerPort="3306";		// 从库服务器
	private Date beginCreateDate;		// 开始 创建时间
	private Date endCreateDate;		 // 结束 创建时间
	private String multiTenantType;  //数据隔离模式

	public String getMultiTenantType() {
		return multiTenantType;
	}

	public void setMultiTenantType(String multiTenantType) {
		this.multiTenantType = multiTenantType;
	}

	public SysTenant() {
		super();
	}

	public SysTenant(String id){
		super(id);
	}

	@Length(min=0, max=20, message="机构编码长度必须介于 0 和 20 之间")
	public String getOrgCode() {
		return orgCode;
	}

	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode;
	}
	
	@Length(min=0, max=255, message="机构名称长度必须介于 0 和 255 之间")
	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}
	
	@Length(min=0, max=255, message="logo地址长度必须介于 0 和 255 之间")
	public String getLogo() {
		return logo;
	}

	public void setLogo(String logo) {
		this.logo = logo;
	}
	
	@Length(min=0, max=255, message="企业简介长度必须介于 0 和 255 之间")
	public String getEnterpriseDescribe() {
		return enterpriseDescribe;
	}

	public void setEnterpriseDescribe(String enterpriseDescribe) {
		this.enterpriseDescribe = enterpriseDescribe;
	}
	
	@Length(min=0, max=255, message="域名长度必须介于 0 和 255 之间")
	public String getEnterpriseDomain() {
		return enterpriseDomain;
	}

	public void setEnterpriseDomain(String enterpriseDomain) {
		this.enterpriseDomain = enterpriseDomain;
	}
	
	@Length(min=0, max=50, message="责任人长度必须介于 0 和 50 之间")
	public String getDuty() {
		return duty;
	}

	public void setDuty(String duty) {
		this.duty = duty;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getExpirationTime() {
		return expirationTime;
	}

	public void setExpirationTime(Date expirationTime) {
		this.expirationTime = expirationTime;
	}
	
	public String getPasswordExpire() {
		return passwordExpire;
	}

	public void setPasswordExpire(String passwordExpire) {
		this.passwordExpire = passwordExpire;
	}
	
	public Integer getIsMultipleLogin() {
		return isMultipleLogin;
	}

	public void setIsMultipleLogin(Integer isMultipleLogin) {
		this.isMultipleLogin = isMultipleLogin;
	}
	
	public String getPasswordErrorNum() {
		return passwordErrorNum;
	}

	public void setPasswordErrorNum(String passwordErrorNum) {
		this.passwordErrorNum = passwordErrorNum;
	}
	
	@Length(min=0, max=50, message="账号锁定时间密码输错${passwordErrorNum}次后，锁定账号的时间单位： h | d | w | m单位： 时 | 天 | 周 | 月如：0=当天晚上24点 2h = 2小时   2d = 2天长度必须介于 0 和 50 之间")
	public String getPasswordErrorLockTime() {
		return passwordErrorLockTime;
	}

	public void setPasswordErrorLockTime(String passwordErrorLockTime) {
		this.passwordErrorLockTime = passwordErrorLockTime;
	}
	
	@Length(min=0, max=10, message="租户状态长度必须介于 0 和 10 之间")
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	@Length(min=0, max=20, message="租户名称长度必须介于 0 和 20 之间")
	public String getTenantName() {
		return tenantName;
	}

	public void setTenantName(String tenantName) {
		this.tenantName = tenantName;
	}
	
	@Length(min=0, max=64, message="租户密码长度必须介于 0 和 64 之间")
	public String getTenantPassword() {
		return tenantPassword;
	}

	public void setTenantPassword(String tenantPassword) {
		this.tenantPassword = tenantPassword;
	}
	
	@Length(min=0, max=11, message="租户手机长度必须介于 0 和 11 之间")
	public String getTenantPhone() {
		return tenantPhone;
	}

	public void setTenantPhone(String tenantPhone) {
		this.tenantPhone = tenantPhone;
	}

	public String getSlaveServerPort() {
		return slaveServerPort;
	}

	public void setSlaveServerPort(String slaveServerPort) {
		this.slaveServerPort = slaveServerPort;
	}

	@Length(min=0, max=15, message="数据库IP长度必须介于 0 和 15 之间")
	public String getMysqlServerIp() {
		return mysqlServerIp;
	}

	public void setMysqlServerIp(String mysqlServerIp) {
		this.mysqlServerIp = mysqlServerIp;
	}
	
	@Length(min=0, max=11, message="数据库端口长度必须介于 0 和 11 之间")
	public String getMysqlPort() {
		return mysqlPort;
	}

	public void setMysqlPort(String mysqlPort) {
		this.mysqlPort = mysqlPort;
	}
	
	@Length(min=0, max=32, message="数据库密码长度必须介于 0 和 32 之间")
	public String getMysqlPassword() {
		return mysqlPassword;
	}

	public void setMysqlPassword(String mysqlPassword) {
		this.mysqlPassword = mysqlPassword;
	}
	
	@Length(min=0, max=10, message="数据库用户名长度必须介于 0 和 10 之间")
	public String getMsyqlUserName() {
		return msyqlUserName;
	}

	public void setMsyqlUserName(String msyqlUserName) {
		this.msyqlUserName = msyqlUserName;
	}
	
	@Length(min=0, max=30, message="数据库名称长度必须介于 0 和 30 之间")
	public String getMysqlDbName() {
		return mysqlDbName;
	}

	public void setMysqlDbName(String mysqlDbName) {
		this.mysqlDbName = mysqlDbName;
	}
	
	public Integer getIsMasterSlave() {
		return isMasterSlave;
	}

	public void setIsMasterSlave(Integer isMasterSlave) {
		this.isMasterSlave = isMasterSlave;
	}
	
	@Length(min=0, max=15, message="从库服务器长度必须介于 0 和 15 之间")
	public String getSlaveServerIp() {
		return slaveServerIp;
	}

	public void setSlaveServerIp(String slaveServerIp) {
		this.slaveServerIp = slaveServerIp;
	}
	
	public Date getBeginCreateDate() {
		return beginCreateDate;
	}

	public void setBeginCreateDate(Date beginCreateDate) {
		this.beginCreateDate = beginCreateDate;
	}
	
	public Date getEndCreateDate() {
		return endCreateDate;
	}

	public void setEndCreateDate(Date endCreateDate) {
		this.endCreateDate = endCreateDate;
	}
		
}