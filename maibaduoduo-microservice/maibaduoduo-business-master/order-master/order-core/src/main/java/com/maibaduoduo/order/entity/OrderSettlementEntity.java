/*
 * Copyright (c) 2019-2023 SAAS开源 All rights reserved. lifengwork@yeah.net
 * SAAS系统设计研发交流
 * https://www.maibaduoduo.com
 */
package com.maibaduoduo.order.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.math.BigDecimal;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 订单结算
 * 
 * @author saas
 * @email lifengwork@yeah.net
 * @date 2023-11-12 16:41:56
 */
@Data
@TableName("saas_order_settlement")
public class OrderSettlementEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 结算ID
	 */
	@TableId
	private Integer id;
	/**
	 * 结算单编码
	 */
	private String settlementNo;
	/**
	 * 订单编号
	 */
	private String orderId;
	/**
	 * 结算订单总金额
	 */
	private BigDecimal goodsTotalAmount;
	/**
	 * 优惠金额
	 */
	private BigDecimal goodsDiscountAmount;
	/**
	 * 运费
	 */
	private BigDecimal freightCharge;
	/**
	 * 支付方式（银联、微信、支付宝）
	 */
	private Integer payType;
	/**
	 * 零售ID
	 */
	private String shopId;
	/**
	 * 发票代码
	 */
	private String invoiceCode;
	/**
	 * 开票日期
	 */
	private Date invoiceDate;
	/**
	 * 发票状态
	 */
	private Integer invoiceStatus;
	/**
	 * 发票金额
	 */
	private BigDecimal invoiceAmount;
	/**
	 * 发票抬头
	 */
	private String invoiceTitle;
	/**
	 * 结算类型
	 */
	private Integer settlementType;
	/**
	 * 结算日期
	 */
	private Date settlementDate;
	/**
	 * 已结算、待结算
	 */
	private Integer settlementStatus;
	/**
	 * 备注
	 */
	private String remarks;
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
