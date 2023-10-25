/*
 * Copyright (c) 2019-2023 SAAS开源 All rights reserved. lifengwork@yeah.net
 * SAAS系统设计研发交流
 * https://www.maibaduoduo.com
 */
package com.maibaduoduo.logistics.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 配送员
 * 
 * @author saas
 * @email lifengwork@yeah.net
 * @date 2023-10-25 18:03:06
 */
@Data
@TableName("saas_logistics_deliveryman")
public class LogisticsDeliverymanEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 配送员ID
	 */
	@TableId
	private Long id;
	/**
	 * 配送人员代码
	 */
	private String deliverymanCode;
	/**
	 * 配送人员名称
	 */
	private String deliverymanName;
	/**
	 * 性别
	 */
	private String deliverymanSex;
	/**
	 * 邮箱
	 */
	private String deliverymanEmail;
	/**
	 * 手机号
	 */
	private String deliverymanPhone;
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
