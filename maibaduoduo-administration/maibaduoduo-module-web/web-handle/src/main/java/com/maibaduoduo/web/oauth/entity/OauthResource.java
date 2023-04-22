package com.maibaduoduo.web.oauth.entity;

import com.maibaduoduo.common.entity.DataEntity;
import org.hibernate.validator.constraints.Length;
import java.util.Date;
/**
 * 认证资源表Entity
 * @author maibaduoduo
 * @version 2021-08-25
 */
public class OauthResource extends DataEntity<OauthResource> {
	
	private static final long serialVersionUID = 1L;
	private String oauthResourceId;		// 资源id
	private String prefix;		// 路由前缀
	private String oauthResourcePath;		// 资源路径
	private String oauthServiceId;		// 服务id
	private String roleId;		// 角色编号
	private Date beginCreateDate;		// 开始 创建时间
	private Date endCreateDate;		// 结束 创建时间
	
	public OauthResource() {
		super();
	}

	public OauthResource(String id){
		super(id);
	}

	@Length(min=0, max=20, message="资源id长度必须介于 0 和 20 之间")
	public String getOauthResourceId() {
		return oauthResourceId;
	}

	public void setOauthResourceId(String oauthResourceId) {
		this.oauthResourceId = oauthResourceId;
	}
	
	@Length(min=1, max=10, message="路由前缀长度必须介于 1 和 10 之间")
	public String getPrefix() {
		return prefix;
	}

	public void setPrefix(String prefix) {
		this.prefix = prefix;
	}
	
	@Length(min=0, max=20, message="资源路径长度必须介于 0 和 20 之间")
	public String getOauthResourcePath() {
		return oauthResourcePath;
	}

	public void setOauthResourcePath(String oauthResourcePath) {
		this.oauthResourcePath = oauthResourcePath;
	}
	
	@Length(min=0, max=20, message="服务id长度必须介于 0 和 20 之间")
	public String getOauthServiceId() {
		return oauthServiceId;
	}

	public void setOauthServiceId(String oauthServiceId) {
		this.oauthServiceId = oauthServiceId;
	}
	
	@Length(min=0, max=64, message="角色编号长度必须介于 0 和 64 之间")
	public String getRoleId() {
		return roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
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