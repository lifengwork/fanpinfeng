/**
 * Copyright (c) 2019-2023 SAAS开源 All rights reserved.
 *
 * SAAS系统设计研发交流
 *
 * https://www.maibaduoduo.com
 */
package com.maibaduoduo.purchase.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.math.BigDecimal;
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
@TableName("saas_purchase")
public class PurchaseEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId
	private Long id;
	/**
	 * 采购项目
	 */
	private String purchaseName;
	/**
	 * 采购描述
	 */
	private String purchaseDesc;
	/**
	 * 采购价格
	 */
	private BigDecimal purchasePrice;
	/**
	 * 采购数量
	 */
	private String purchaseCount;
	/**
	 * 采购类型
	 */
	private String purchaseType;
	/**
	 * 创建者ID
	 */
	private Long createUserId;
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
