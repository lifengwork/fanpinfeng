/*
 * Copyright (c) 2019-2023 SAAS开源 All rights reserved. lifengwork@yeah.net
 * SAAS系统设计研发交流
 * https://www.maibaduoduo.com
 */
package com.maibaduoduo.scp.entity;

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
 * @date 2023-11-18 22:34:39
 */
@Data
@TableName("saas_scp_financial")
public class ScpFinancialEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 财务绩效ID
	 */
	@TableId
	private Long id;
	/**
	 * 财务绩效代码
	 */
	private String scpFinancialCode;
	/**
	 * 现金流量周期
	 */
	private String scpCashTimeCycle;
	/**
	 * 供应链管理总成本
	 */
	private BigDecimal scpTotalSupplyChainManagementCosts;
	/**
	 * 应收账款周转天数
	 */
	private Integer scpDaySalesOutstanding;
	/**
	 * 财务绩效状态
	 */
	private Integer scpFinancialStatus;
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
