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
 * 仓储表
 * 
 * @author saas
 * @email lifengwork@yeah.net
 * @date 2023-11-12 11:48:30
 */
@Data
@TableName("saas_store")
public class StoreEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 仓库Id
	 */
	@TableId
	private Long id;
	/**
	 * 仓库编码
	 */
	private Integer storeNo;
	/**
	 * 仓库名称
	 */
	private String storeName;
	/**
	 * 仓库简述
	 */
	private String storeDescription;
	/**
	 * 仓库总面积
	 */
	private Double storeArea;
	/**
	 * 总库位数量
	 */
	private Integer storeQuantity;
	/**
	 * 仓库所在区域代码
	 */
	private String storeAreaCode;
	/**
	 * 仓库所在区域名称
	 */
	private String storeAreaName;
	/**
	 * 是否可扩容0否1是
	 */
	private Integer ensureCapacity;
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
