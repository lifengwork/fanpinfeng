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
 * 利润表
 * 
 * @author saas
 * @email lifengwork@yeah.net
 * @date 2023-11-17 15:12:24
 */
@Data
@TableName("saas_income")
public class IncomeEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 利润ID
	 */
	@TableId
	private Long id;
	/**
	 * 营业收入
	 */
	private BigDecimal operatingRevenue;
	/**
	 * 销售成本
	 */
	private BigDecimal salesCosts;
	/**
	 * 营业利润
	 */
	private BigDecimal operatingProfit;
	/**
	 * 管理费用
	 */
	private BigDecimal administrativeExpenses;
	/**
	 * 销售费用
	 */
	private BigDecimal marketingExpensessellingExpenses;
	/**
	 * 财务费用
	 */
	private BigDecimal financialResult;
	/**
	 * 利润总额
	 */
	private BigDecimal totalProfit;
	/**
	 * 所得税
	 */
	private BigDecimal incomeTax;
	/**
	 * 净利润
	 */
	private BigDecimal netIncome;
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
