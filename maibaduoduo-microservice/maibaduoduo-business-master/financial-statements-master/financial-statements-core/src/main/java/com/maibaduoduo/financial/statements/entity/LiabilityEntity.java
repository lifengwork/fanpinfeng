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
 * 负债表
 * 
 * @author saas
 * @email lifengwork@yeah.net
 * @date 2023-11-17 15:12:24
 */
@Data
@TableName("saas_liability")
public class LiabilityEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 负债ID
	 */
	@TableId
	private Long id;
	/**
	 * 供应商编码
	 */
	private String supllyId;
	/**
	 * 短期借款
	 */
	private BigDecimal shorttermBankLoans;
	/**
	 * 长期借贷
	 */
	private BigDecimal longtermBankLoans;
	/**
	 * 应付账款
	 */
	private BigDecimal accounts;
	/**
	 * 总负债
	 */
	private BigDecimal totalLiability;
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
