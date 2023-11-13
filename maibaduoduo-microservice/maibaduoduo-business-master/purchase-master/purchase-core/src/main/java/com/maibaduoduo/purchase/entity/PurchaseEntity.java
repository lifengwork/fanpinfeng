/*
 * Copyright (c) 2019-2023 SAAS开源 All rights reserved. lifengwork@yeah.net
 * SAAS系统设计研发交流
 * https://www.maibaduoduo.com
 */
package com.maibaduoduo.purchase.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import lombok.Data;

/**
 * 
 * 
 * @author saas
 * @email lifengwork@yeah.net
 * @date 2023-11-12 21:11:37
 */
@Data
@TableName("saas_purchase")
public class PurchaseEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 采购ID
	 */
	@TableId
	private Long id;
	/**
	 * 采购编码
	 */
	private String purchaseNo;
	/**
	 * 采购项目
	 */
	private String purchaseName;
	/**
	 * 采购描述
	 */
	private String purchaseDesc;
	/**
	 * 采购总数量
	 */
	private Integer purchaseTotalCount;
	/**
	 * 采购总金额
	 */
	private BigDecimal purchaseTotalMoney;
	/**
	 * 采购类型(招标、询价、其他)
	 */
	private String purchaseType;
	/**
	 * 采购人ID
	 */
	private String userId;
	/**
	 * 合同编码
	 */
	private String purchaseContractId;
	/**
	 * 创建者ID
	 */
	private Long createUserId;
	/**
	 * 创建时间
	 */
	private Date createTime;
	/**
	 * 是否启用0禁用1启用
	 */
	private String status;
	/**
	 * 租户编码
	 */
	private String tenantId;

}
