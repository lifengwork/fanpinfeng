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
 * 金融机构表
 * 
 * @author saas
 * @email lifengwork@yeah.net
 * @date 2023-11-17 15:01:07
 */
@Data
@TableName("saas_financial_institution")
public class FinancialInstitutionEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 金融机构ID
	 */
	@TableId
	private Long id;
	/**
	 * 金融机构代码
	 */
	private String financialInstitutionCode;
	/**
	 * 金融机构名称
	 */
	private String financialInstitutionName;
	/**
	 * 金融机构类型
	 */
	private String financialInstitutionType;
	/**
	 * 融资模式
	 */
	private Integer financialInstitutionMode;
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
