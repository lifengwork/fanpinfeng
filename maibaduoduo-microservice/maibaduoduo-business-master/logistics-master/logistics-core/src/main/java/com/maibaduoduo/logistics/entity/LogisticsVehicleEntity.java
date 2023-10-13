/**
 * Copyright (c) 2019-2023 SAAS开源 All rights reserved.
 *
 * SAAS系统设计研发交流
 *
 * https://www.maibaduoduo.com
 */
package com.maibaduoduo.logistics.entity;

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
 * @date 2023-06-25 09:24:11
 */
@Data
@TableName("saas_logistics_vehicle")
public class LogisticsVehicleEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId
	private Long id;
	/**
	 * 名称
	 */
	private String vehicleName;
	/**
	 * 简述
	 */
	private String vehicleDesc;
	/**
	 * 运输、转运
	 */
	private String vehicleType;
	/**
	 * 地址
	 */
	private String vehicleAddress;
	/**
	 * 手机
	 */
	private String vehicleMobile;
	/**
	 * 邮箱
	 */
	private String vehicleEmail;
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

}
