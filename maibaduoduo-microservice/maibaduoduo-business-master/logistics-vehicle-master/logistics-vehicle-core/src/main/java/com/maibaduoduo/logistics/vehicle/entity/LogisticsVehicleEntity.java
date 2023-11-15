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
 * 车辆
 * 
 * @author saas
 * @email lifengwork@yeah.net
 * @date 2023-11-13 13:29:44
 */
@Data
@TableName("saas_logistics_vehicle")
public class LogisticsVehicleEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 运输方式ID
	 */
	@TableId
	private Long id;
	/**
	 * 名称
	 */
	private String vehicleName;
	/**
	 * 运输ID
	 */
	private String transportId;
	/**
	 * 简述
	 */
	private String vehicleDesc;
	/**
	 * 陆运、空运、水运
	 */
	private String vehicleType;
	/**
	 * 运输工具标识（车牌号、航班号等）
	 */
	private String vehicleIdentification;
	/**
	 * 当前运输工具状态
	 */
	private Integer vehicleStatus;
	/**
	 * 当前所在位置
	 */
	private String vehicleCurrentPosition;
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
