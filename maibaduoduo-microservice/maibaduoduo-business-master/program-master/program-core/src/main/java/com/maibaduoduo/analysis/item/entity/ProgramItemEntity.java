/**
 * Copyright (c) 2019-2023 SAAS开源 All rights reserved.
 *
 * SAAS系统设计研发交流
 *
 * https://www.maibaduoduo.com
 */
package com.maibaduoduo.analysis.item.entity;

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
 * @date 2023-05-06 20:24:36
 */
@Data
@TableName("saas_program_item")
public class ProgramItemEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 执行计划项编码
	 */
	@TableId
	private Long id;
	/**
	 * 执行计划编码
	 */
	private Long programId;
	/**
	 * 操作人
	 */
	private String programItemOperator;
	/**
	 * 执行状态
	 */
	private String programItemStatus;
	/**
	 * 执行时间
	 */
	private Date programItemDate;
	/**
	 * 租户编码
	 */
	private Long tenantId;
	/**
	 * 创建者
	 */
	private Long createUserId;
	/**
	 * 创建时间
	 */
	private Date createTime;

}
