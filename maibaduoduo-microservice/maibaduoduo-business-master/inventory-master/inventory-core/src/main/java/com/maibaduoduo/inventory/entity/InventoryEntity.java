/*
 * Copyright (c) 2019-2023 SAAS开源 All rights reserved. lifengwork@yeah.net
 * SAAS系统设计研发交流
 * https://www.maibaduoduo.com
 */
package com.maibaduoduo.inventory.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 中央仓库
 * 
 * @author saas
 * @email lifengwork@yeah.net
 * @date 2023-11-12 11:48:30
 */
@Data
@TableName("saas_inventory")
public class InventoryEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 库存ID
	 */
	@TableId
	private Long id;
	/**
	 * 库存编码
	 */
	private String inventoryCode;
	/**
	 * 简要描述
	 */
	private String inventoryDesc;
	/**
	 * 仓库编码 
	 */
	private String storeId;
	/**
	 * SKU ID  110975910
	 */
	private Long skuId;
	/**
	 * 库存状态 合格
	 */
	private String status;
	/**
	 * 分配库存 200
	 */
	private Integer assignInventory;
	/**
	 * 实物库存 2000
	 */
	private Integer entityInventory;
	/**
	 * 冻结库存 500
	 */
	private Integer frozenInventory;
	/**
	 * 在线库存 100
	 */
	private Integer onlineInventory;
	/**
	 * 库存是否全部冻结 否
	 */
	private String frozenAll;
	/**
	 * 可销售库存 100
	 */
	private Integer sellInventory;
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
