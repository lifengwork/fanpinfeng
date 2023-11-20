/*
 * Copyright (c) 2019-2023 SAAS开源 All rights reserved. lifengwork@yeah.net
 * SAAS系统设计研发交流
 * https://www.maibaduoduo.com
 */
package com.maibaduoduo.payment.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.math.BigDecimal;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 
 * 
 * @author saas
 * @email lifengwork@yeah.net
 * @date 2023-11-20 20:56:57
 */
@Data
@TableName("saas_purchase_payment")
public class PurchasePaymentEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 付款ID
	 */
	@TableId
	private Long id;
	/**
	 * 付款编码
	 */
	private String paymentNo;
	/**
	 * 订单编码
	 */
	private String orderId;
	/**
	 * 付款金额
	 */
	private BigDecimal paymentMoney;
	/**
	 * 付款日期
	 */
	private Date paymentDate;
	/**
	 * 采购单编码
	 */
	private String purchaseId;
	/**
	 * 付款状态
	 */
	private Integer paymentStatus;
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
