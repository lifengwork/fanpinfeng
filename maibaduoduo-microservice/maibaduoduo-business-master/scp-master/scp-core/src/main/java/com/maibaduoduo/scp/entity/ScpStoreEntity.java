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
 * @date 2023-11-18 21:50:57
 */
@Data
@TableName("saas_scp_store")
public class ScpStoreEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 仓储绩效ID
	 */
	@TableId
	private Long id;
	/**
	 * 仓储绩效代码
	 */
	private String scpStoreCode;
	/**
	 * 仓储绩效类型（收料及时率、发货及时率、收料准确率、发货准确率、库存准确率、货损率、库位利用率）
	 */
	private Integer scpStoreKpi;
	/**
	 * 总和
	 */
	private String scpStoreTotal;
	/**
	 * 目标
	 */
	private String scpStoreGoal;
	/**
	 * 当月实际量
	 */
	private String scpStoreActualQuantityMonth;
	/**
	 * 目标达成度
	 */
	private String scpStoreGoalAchievementDegree;
	/**
	 * 权重（总和为100）
	 */
	private String scpStoreWeight;
	/**
	 * 目标达成度*权重
	 */
	private String scpStoreGoalAchievementDegreeWeight;
	/**
	 * 档期绩效目标
	 */
	private String scpStoreCurrentGoal;
	/**
	 * 当期绩效评估
	 */
	private String scpStoreCurrentAssessment;
	/**
	 * 绩效状态
	 */
	private Integer scpStoreStatus;
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
