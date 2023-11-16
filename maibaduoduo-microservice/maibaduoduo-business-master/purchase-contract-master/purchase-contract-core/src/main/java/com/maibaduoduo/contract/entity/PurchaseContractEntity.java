/*
 * Copyright (c) 2019-2023 SAAS开源 All rights reserved. lifengwork@yeah.net
 * SAAS系统设计研发交流
 * https://www.maibaduoduo.com
 */
package com.maibaduoduo.contract.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 
 * 
 * @author saas
 * @email lifengwork@yeah.net
 * @date 2023-11-13 21:59:29
 */
@Data
@TableName("saas_purchase_contract")
public class PurchaseContractEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 合同ID
	 */
	@TableId
	private Long id;
	/**
	 * 合同编码
	 */
	private String contractNo;
	/**
	 * 合同
	 */
	private String contractDoc;
	/**
	 * 供应商编码
	 */
	private String supplyId;
	/**
	 * 采购单编码
	 */
	private String purchaseId;
	/**
	 * 合同状态1成立2生效3执行
	 */
	private Integer contractStatus;
	/**
	 * 合同类型
	 */
	private Integer contractType;
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
