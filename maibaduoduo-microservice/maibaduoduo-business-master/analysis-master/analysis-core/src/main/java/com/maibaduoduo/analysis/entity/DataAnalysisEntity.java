/**
 * Copyright (c) 2019-2023 SAAS开源 All rights reserved.
 *
 * SAAS系统设计研发交流
 *
 * https://www.maibaduoduo.com
 */
package com.maibaduoduo.analysis.entity;

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
 * @date 2023-05-13 21:00:36
 */
@Data
@TableName("saas_data_analysis")
public class DataAnalysisEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId
	private Long id;
	/**
	 * 分析项目
	 */
	private String analysisName;
	/**
	 * 数据类型
	 */
	private String dataType;
	/**
	 * 创建者ID
	 */
	private Long createUserId;
	/**
	 * 分析数据
	 */
	private String analysisContent;
	/**
	 * 创建时间
	 */
	private Date createTime;
	/**
	 * 是否启用0禁用1启用
	 */
	private String status;
	/**
	 * 租户编码
	 */
	private String tenantId;

}
