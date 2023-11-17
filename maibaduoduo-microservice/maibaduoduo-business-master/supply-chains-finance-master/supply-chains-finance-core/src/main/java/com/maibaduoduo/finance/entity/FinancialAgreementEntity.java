/*
 * Copyright (c) 2019-2023 SAAS开源 All rights reserved. lifengwork@yeah.net
 * SAAS系统设计研发交流
 * https://www.maibaduoduo.com
 */
package com.maibaduoduo.finance.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 融资协议
 * 
 * @author saas
 * @email lifengwork@yeah.net
 * @date 2023-11-17 15:01:07
 */
@Data
@TableName("saas_financial_agreement")
public class FinancialAgreementEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 融资协议ID
	 */
	@TableId
	private Long id;
	/**
	 * 融资标的代码
	 */
	private String financialTargetCode;
	/**
	 * 协议代码
	 */
	private String agreementCode;
	/**
	 * 协议名称
	 */
	private String agreementName;
	/**
	 * 协议简述
	 */
	private String agreementDesc;
	/**
	 * 协议
	 */
	private String agreementDoc;
	/**
	 * 协议生效日期
	 */
	private Date agreementEffectiveDate;
	/**
	 * 签章
	 */
	private String agreementSignature;
	/**
	 * 协议状态
	 */
	private Integer agreementStatus;
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
