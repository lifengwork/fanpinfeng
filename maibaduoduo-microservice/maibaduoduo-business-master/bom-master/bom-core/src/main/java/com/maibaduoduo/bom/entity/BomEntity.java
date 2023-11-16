/*
 * Copyright (c) 2019-2023 SAAS开源 All rights reserved. lifengwork@yeah.net
 * SAAS系统设计研发交流
 * https://www.maibaduoduo.com
 */
package com.maibaduoduo.bom.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.math.BigDecimal;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 产品物料清单
 * 
 * @author saas
 * @email lifengwork@yeah.net
 * @date 2023-11-14 12:12:51
 */
@Data
@TableName("saas_bom")
public class BomEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 物料ID
	 */
	@TableId
	private Long id;
	/**
	 * 物料编码
	 */
	private String materialsNo;
	/**
	 * 物品名称
	 */
	private String goodsName;
	/**
	 * 物品代码
	 */
	private String goodsNo;
	/**
	 * 设计来源编码
	 */
	private Integer designId;
	/**
	 * 物料描述
	 */
	private String materialsDesc;
	/**
	 * 库存
	 */
	private Integer materialsStock;
	/**
	 * 物料名称
	 */
	private String materialsName;
	/**
	 * 计量单位
	 */
	private String materialsUnit;
	/**
	 * 提前期
	 */
	private Date materialsLeadTime;
	/**
	 * 间接成本
	 */
	private BigDecimal materialsIndirectCost;
	/**
	 * 直接成本
	 */
	private BigDecimal materialsDirectCost;
	/**
	 * 总成本
	 */
	private BigDecimal materialsTotalCost;
	/**
	 * 安全库存
	 */
	private Integer materialsSafetyStock;
	/**
	 * 存放仓库
	 */
	private String materialsStore;
	/**
	 * 采购周期
	 */
	private String materialsPurchaseCycle;
	/**
	 * 上层物料序号
	 */
	private Long materialsUpSn;
	/**
	 * 下层物料序号
	 */
	private Long materialsDownSn;
	/**
	 * 供应商编码
	 */
	private Long supplyId;
	/**
	 * 物料状态
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
