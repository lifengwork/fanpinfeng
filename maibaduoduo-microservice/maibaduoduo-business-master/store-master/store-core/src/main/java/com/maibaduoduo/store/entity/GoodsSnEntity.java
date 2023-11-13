/*
 * Copyright (c) 2019-2023 SAAS开源 All rights reserved. lifengwork@yeah.net
 * SAAS系统设计研发交流
 * https://www.maibaduoduo.com
 */
package com.maibaduoduo.store.entity;

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
 * @date 2023-11-12 18:04:34
 */
@Data
@TableName("saas_goods_sn")
public class GoodsSnEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * SNID（产品系列）
	 */
	@TableId
	private Long id;
	/**
	 * SN编码（SN1：C30Q21J1235）
	 */
	private String snNo;
	/**
	 * SKUID
	 */
	private String skuNo;
	/**
	 * SN项
	 */
	private String snItem1;
	/**
	 * 
	 */
	private String snItem2;
	/**
	 * 
	 */
	private String snItem3;
	/**
	 * 
	 */
	private String snItem4;
	/**
	 * 
	 */
	private String snItem5;
	/**
	 * 
	 */
	private String snItem6;
	/**
	 * 
	 */
	private String snItem7;
	/**
	 * 
	 */
	private String snItem8;
	/**
	 * 
	 */
	private String snItem9;
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
