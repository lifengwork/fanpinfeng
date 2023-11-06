/*
 * Copyright (c) 2019-2023 SAAS开源 All rights reserved. lifengwork@yeah.net
 * SAAS系统设计研发交流
 * https://www.maibaduoduo.com
 */
package com.maibaduoduo.logistics.transport.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 运输
 * 
 * @author saas
 * @email lifengwork@yeah.net
 * @date 2023-10-25 18:03:06
 */
@Data
@TableName("saas_logistics_transport")
public class LogisticsTransportEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 运输ID
	 */
	@TableId
	private Long id;
	/**
	 * 运输代码
	 */
	private String transportCode;
	/**
	 * 配送ID
	 */
	private Long deliveryId;
	/**
	 * 配送员
	 */
	private Long deliverymanId;
	/**
	 * 运输方式
	 */
	private Long vehicleId;
	/**
	 * 名称
	 */
	private String transportName;
	/**
	 * 简述
	 */
	private String transportDesc;
	/**
	 * 地址
	 */
	private String transportAddress;
	/**
	 * 手机
	 */
	private Integer transportMobile;
	/**
	 * 邮箱
	 */
	private String transportEmail;
	/**
	 * 当前运输状态（待配送、配送中、已完成、已停运）
	 */
	private Integer transportStatus;
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
