/*
 * Copyright (c) 2019-2023 SAAS开源 All rights reserved. lifengwork@yeah.net
 * SAAS系统设计研发交流
 * https://www.maibaduoduo.com
 */
package com.maibaduoduo.logistics.delivery.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 配送
 * 
 * @author saas
 * @email lifengwork@yeah.net
 * @date 2023-10-25 18:03:06
 */
@Data
@TableName("saas_logistics_delivery")
public class LogisticsDeliveryEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 配送ID
	 */
	@TableId
	private Long id;
	/**
	 * 配送代码
	 */
	private String delliveryCode;
	/**
	 * 订单编码
	 */
	private String orderId;
	/**
	 * 仓库编码
	 */
	private String storeId;
	/**
	 * 配送名称
	 */
	private String deliveryName;
	/**
	 * 简述
	 */
	private String deliveryDesc;
	/**
	 * 地址
	 */
	private String deliveryAddress;
	/**
	 * 手机
	 */
	private Integer deliveryMobile;
	/**
	 * 邮箱
	 */
	private String deliveryEmail;
	/**
	 * 2部分在途、1全部配送完成、0失效停配
	 */
	private Integer deliveryStatus;
	/**
	 * 状态
	 */
	private Integer status;
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
