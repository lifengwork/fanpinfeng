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
 * 担保人征信
 * 
 * @author saas
 * @email lifengwork@yeah.net
 * @date 2023-11-17 15:01:07
 */
@Data
@TableName("saas_financial_ensure_credit")
public class FinancialEnsureCreditEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 担保ID
	 */
	@TableId
	private Long id;
	/**
	 * 担保人名称
	 */
	private String ensureName;
	/**
	 * 担保人征信
	 */
	private String ensureCredit;
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
