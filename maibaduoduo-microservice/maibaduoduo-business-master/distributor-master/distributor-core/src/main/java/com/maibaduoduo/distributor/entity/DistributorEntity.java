/**
 * Copyright (c) 2019-2023 SAAS开源 All rights reserved.
 *
 * SAAS系统设计研发交流
 *
 * https://www.maibaduoduo.com
 */
package com.maibaduoduo.distributor.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 分销
 * 
 * @author saas
 * @email lifengwork@yeah.net
 * @date 2023-06-25 09:24:11
 */
@Data
@TableName("saas_distributor")
public class DistributorEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId
	private Long id;
	/**
	 * 分销名称
	 */
	private String distributorName;
	/**
	 * 分销商类型(代理商、批发商)
	 */
	private String distributorType;
	/**
	 * 分销商描述
	 */
	private String distributorDesc;
	/**
	 * 手机号
	 */
	private String distributorMobile;
	/**
	 * 邮箱
	 */
	private String distributorEmail;
	/**
	 * 状态
	 */
	private String status;
	/**
	 * 创建者ID
	 */
	private Long createUserId;
	/**
	 * 创建时间
	 */
	private Date createTime;
	/**
	 * 租户编码
	 */
	private String tenantId;

}
