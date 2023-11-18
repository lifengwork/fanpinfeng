/*
 * Copyright (c) 2019-2023 SAAS开源 All rights reserved. lifengwork@yeah.net
 * SAAS系统设计研发交流
 * https://www.maibaduoduo.com
 */
package com.maibaduoduo.financial.statements.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.math.BigDecimal;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 资产表
 * 
 * @author saas
 * @email lifengwork@yeah.net
 * @date 2023-11-17 15:12:24
 */
@Data
@TableName("saas_assets")
public class AssetsEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 指标编码
	 */
	@TableId
	private Long id;
	/**
	 * 供应商编码
	 */
	private String supllyId;
	/**
	 * 现金
	 */
	private BigDecimal cash;
	/**
	 * 应收账款
	 */
	private BigDecimal tradeAccountsReceivable;
	/**
	 * 存货
	 */
	private BigDecimal inventories;
	/**
	 * 固定资产原值
	 */
	private BigDecimal originalFixedAssets;
	/**
	 * 固定资产净值
	 */
	private BigDecimal netFixedAssets;
	/**
	 * 累计折旧
	 */
	private BigDecimal accumulatedDpreciation;
	/**
	 * 总资产
	 */
	private BigDecimal totalAssets;
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
