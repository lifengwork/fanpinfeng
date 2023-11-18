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
 * @author saas
 * @email lifengwork@yeah.net
 * @date 2023-11-18 21:50:57
 */
@Data
@TableName("saas_scp_transport")
public class ScpTransportEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 融资协议ID
	 */
	@TableId
	private Long id;
	/**
	 * 运输绩效代码
	 */
	private String scpTransprotCode;
	/**
	 * 绩效KPI（发货提货准时率、匀速车辆整洁率、到货准时率、运输正确率、
	 * 回单准时率、送货信息反馈率、对账及时率、客户投诉率、紧急响应率）
	 */
	private Integer scpTransprotKpi;
	/**
	 * 绩效指标得分总和
	 */
	private String scpTransprotKpiScoreTotal;
	/**
	 * 目标
	 */
	private String scpTransprotGoal;
	/**
	 * 总笔数
	 */
	private String scpTransprotTotalNumber;
	/**
	 * 异常或发生笔数
	 */
	private String scpTransprotExceptionNumber;
	/**
	 * 当月实际值
	 */
	private String scpTransprotActualNumber;
	/**
	 * 档期绩效目标
	 */
	private String scpTransprotCurrentGoal;
	/**
	 * 当期绩效评估
	 */
	private String scpTransprotCurrentAssessment;
	/**
	 * 目标达成度
	 */
	private String scpTransprotGoalAchievementDegree;
	/**
	 * 权重（总和为100）
	 */
	private String scpTransprotWeight;
	/**
	 * 目标达成度*权重
	 */
	private String scpTransprotGoalAchievementDegreeWeight;
	/**
	 * 运输绩效状态
	 */
	private Integer scpTransprotStatus;
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
