/*
 * Copyright (c) 2019-2023 SAAS开源 All rights reserved. lifengwork@yeah.net
 * SAAS系统设计研发交流
 * https://www.maibaduoduo.com
 */
package com.maibaduoduo.logistics.vehicle.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 具体交通工具管理表
 * 
 * @author saas
 * @email lifengwork@yeah.net
 * @date 2023-11-14 10:20:32
 */
@Data
@TableName("saas_vehicle")
public class VehicleEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 运输工具ID
	 */
	@TableId
	private Long id;
	/**
	 * 运输工具编码
	 */
	private String vehicleCode;
	/**
	 * 运输工具名称
	 */
	private String vehicleName;
	/**
	 * 陆运、空运、水运
	 */
	private String vehicleType;
	/**
	 * 运输工具标识（车牌号等）
	 */
	private String vehicleIdentification;
	/**
	 * 运输工具简述
	 */
	private String vehicleDesc;
	/**
	 * 容量（体积-承载）
	 */
	private String vehicleCapacity;
	/**
	 * 监管人员
	 */
	private String vehicleSupervisor;
	/**
	 * 可用调度日期
	 */
	private Date vehicleSchedulingPeriod;
	/**
	 * 状态(启用、停用、在途)
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
