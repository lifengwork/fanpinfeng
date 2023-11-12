/*
 * Copyright (c) 2019-2023 SAAS开源 All rights reserved. lifengwork@yeah.net
 * SAAS系统设计研发交流
 * https://www.maibaduoduo.com
 */
package com.maibaduoduo.store.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.math.BigDecimal;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 运费
 * 
 * @author saas
 * @email lifengwork@yeah.net
 * @date 2023-11-12 11:48:30
 */
@Data
@TableName("saas_freight_charge")
public class FreightChargeEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 运费ID
	 */
	@TableId
	private Long id;
	/**
	 * 运费编码
	 */
	private String freightChargeNo;
	/**
	 * 订单编码
	 */
	private String orderNo;
	/**
	 * 仓库编码
	 */
	private String storeNo;
	/**
	 * 用户编码
	 */
	private String userId;
	/**
	 * 运费
	 */
	private BigDecimal freightCharge;
	/**
	 * 物品SKU
	 */
	private String goodsSkuId;
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
