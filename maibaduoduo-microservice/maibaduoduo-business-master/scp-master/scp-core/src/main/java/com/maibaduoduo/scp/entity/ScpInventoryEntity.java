/*
 * Copyright (c) 2019-2023 SAAS开源 All rights reserved. lifengwork@yeah.net
 * SAAS系统设计研发交流
 * https://www.maibaduoduo.com
 */
package com.maibaduoduo.scp.entity;

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
 * @date 2023-11-18 22:15:36
 */
@Data
@TableName("saas_scp_inventory")
public class ScpInventoryEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 融资协议ID
	 */
	@TableId
	private Long id;
	/**
	 * 库存绩效代码
	 */
	private String scpInventoryCode;
	/**
	 * 库存周转率
	 */
	private String scpInventoryTurnover;
	/**
	 * 库存可用天数
	 */
	private String scpInventoryDos;
	/**
	 * 库存差异率
	 */
	private String scpInventoryQuantityVariance;
	/**
	 * 差异类型（1数量差异、2生产日期或者批号差异、3质量状态差异...）
	 */
	private Integer scpInventoryQuantityVarianceType;
	/**
	 * 库存满足率
	 */
	private String scpInventorySatisfactionRatio;
	/**
	 * 库存绩效状态
	 */
	private Integer scpInventoryStatus;
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
