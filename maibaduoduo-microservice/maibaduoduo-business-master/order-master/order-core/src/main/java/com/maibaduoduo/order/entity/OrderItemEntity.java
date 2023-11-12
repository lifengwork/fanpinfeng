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
 * @date 2023-11-12 16:41:56
 */
@Data
@TableName("saas_order_item")
public class OrderItemEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 订单项ID
	 */
	@TableId
	private Long id;
	/**
	 * 订单项编码
	 */
	private String orderItemNo;
	/**
	 * 订单编码
	 */
	private String orderId;
	/**
	 * 产品ID
	 */
	private String prodId;
	/**
	 * 产品名称
	 */
	private String prodName;
	/**
	 * 产品图片路径
	 */
	private String prodPic;
	/**
	 * 产品价格
	 */
	private BigDecimal price;
	/**
	 * 产品金额
	 */
	private BigDecimal productAmount;
	/**
	 * 用户编码
	 */
	private Long userId;
	/**
	 * 评论状态： 0 未评价  1 已评价
	 */
	private Integer commStatus;
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
