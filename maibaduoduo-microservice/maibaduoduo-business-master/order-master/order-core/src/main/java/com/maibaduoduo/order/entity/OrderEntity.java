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
 * 
 * 
 * @author saas
 * @email lifengwork@yeah.net
 * @date 2023-11-12 16:43:29
 */
@Data
@TableName("saas_order")
public class OrderEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 订单ID
	 */
	@TableId
	private Long id;
	/**
	 * 订单编码
	 */
	private String orderNo;
	/**
	 * 下单门店
	 */
	private Long shopId;
	/**
	 * 产品名称,多个产品将会以逗号隔开
	 */
	private String prodName;
	/**
	 * 物品编码
	 */
	private String prodId;
	/**
	 *  订购用户ID
	 */
	private Integer userId;
	/**
	 * 订购流水号
	 */
	private Integer orderNumber;
	/**
	 * 订单类型
	 */
	private String orderType;
	/**
	 * 总值
	 */
	private BigDecimal total;
	/**
	 * 实际总值
	 */
	private BigDecimal actualTotal;
	/**
	 * 支付方式 1 微信支付 2 支付宝
	 */
	private String payType;
	/**
	 * 订单备注
	 */
	private String remarks;
	/**
	 * 订单状态 -1 已取消 0:待付款 1:待发货 2:待收货 3:已完成
	 */
	private String status;
	/**
	 * 配送单号
	 */
	private String dvyFlowId;
	/**
	 * 订单运费
	 */
	private BigDecimal freightAmount;
	/**
	 * 用户订单地址Id
	 */
	private String addrOrderId;
	/**
	 * 订单商品总数
	 */
	private String productNums;
	/**
	 * 优惠总额
	 */
	private String reduceAmount;
	/**
	 * 退款状态
	 */
	private Integer refundStatus;
	/**
	 * 是否已经支付，1：已经支付过，0：，没有支付过
	 */
	private String isPayed;
	/**
	 * 取消时间
	 */
	private Date cancelTime;
	/**
	 * 完成时间
	 */
	private Date finallyTime;
	/**
	 * 发货时间
	 */
	private Date dvyTime;
	/**
	 * 付款时间
	 */
	private Date payTime;
	/**
	 * 订单关闭原因 1-超时未支付 2-退款关闭 4-买家取消 15-已通过货到付款交易
	 */
	private String closeType;
	/**
	 * 区域编码
	 */
	private String shopAddrCode;
	/**
	 * 订单更新时间
	 */
	private Date updateTime;
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
