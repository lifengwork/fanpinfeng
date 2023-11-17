/*
 * Copyright (c) 2019-2023 SAAS开源 All rights reserved. lifengwork@yeah.net
 * SAAS系统设计研发交流
 * https://www.maibaduoduo.com
 */
package com.maibaduoduo.finance.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.math.BigDecimal;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 融资单表
 * 
 * @author saas
 * @email lifengwork@yeah.net
 * @date 2023-11-17 15:01:07
 */
@Data
@TableName("saas_financial_target")
public class FinancialTargetEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 金融机构ID
	 */
	@TableId
	private Long id;
	/**
	 * 融资标的代码(意向、订单、质押、票据、提单、装运单等)
	 */
	private String targetCode;
	/**
	 * 融资机构代码
	 */
	private String financialInstitutionCode;
	/**
	 * 受益人
	 */
	private String financialBenefit;
	/**
	 * 担保人
	 */
	private String financialEnsure;
	/**
	 * 融资金额（放款）
	 */
	private BigDecimal financialMoney;
	/**
	 * 划拨款项
	 */
	private BigDecimal financialRemitMoney;
	/**
	 * 偿还债务
	 */
	private BigDecimal financialRepayment;
	/**
	 * 账期
	 */
	private Integer financialAccountPeriod;
	/**
	 * 融资利率
	 */
	private BigDecimal financialInterestRate;
	/**
	 * 预期催收（1正常-1逾期）
	 */
	private Integer financialOverdue;
	/**
	 * 融投状态
	 */
	private Integer financialStatus;
	/**
	 * 融资模式
	 */
	private Integer financialType;
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
