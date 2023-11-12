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
 * 仓储库存
 * 
 * @author saas
 * @email lifengwork@yeah.net
 * @date 2023-11-12 11:48:30
 */
@Data
@TableName("saas_store_inventory")
public class StoreInventoryEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 库存ID
	 */
	@TableId
	private Long id;
	/**
	 * 仓库库存编码
	 */
	private String storeInventory;
	/**
	 * 物品名称
	 */
	private String goodsName;
	/**
	 * 物品ID
	 */
	private String goodsId;
	/**
	 * 货位ID
	 */
	private String storageLocationId;
	/**
	 * 该物品再特定货位上的数量（ 具体货架存放的数量）
	 */
	private Integer inventoryCount;
	/**
	 * 该物品再所有货位上的数量
	 */
	private Integer inventoryTotalCount;
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
