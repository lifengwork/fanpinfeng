/*
 * Copyright (c) 2019-2023 SAAS开源 All rights reserved. lifengwork@yeah.net
 * SAAS系统设计研发交流
 * https://www.maibaduoduo.com
 */
package com.maibaduoduo.logistics.transport.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.math.BigDecimal;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 
 * 海运提单
 * @author saas
 * @email lifengwork@yeah.net
 * @date 2023-11-20 10:53:45
 */
@Data
@TableName("saas_sea_bol")
public class SeaBolEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 海运提单ID
	 */
	@TableId
	private Long id;
	/**
	 * B/L NO.
	 */
	private String blNo;
	/**
	 * 托运人
	 */
	private String shipper;
	/**
	 * 收货人
	 */
	private String consignee;
	/**
	 * 通知人
	 */
	private String notifyParty;
	/**
	 * 前程运输
	 */
	private String prCarriageBy;
	/**
	 * 收货地
	 */
	private String placeReceipt;
	/**
	 * 船名及航次
	 */
	private String oceanVesselVoyNo;
	/**
	 * 装货港
	 */
	private String portLoading;
	/**
	 * 卸货港
	 */
	private String portDischarge;
	/**
	 * 交货地
	 */
	private String placeDelivery;
	/**
	 * 目的地
	 */
	private String finalDestinationMerchantsReference;
	/**
	 * 唛头
	 */
	private String marks;
	/**
	 * 包装种类和数量
	 */
	private String nosKindsPkgs;
	/**
	 * 货物名称
	 */
	private String descriptionGoods;
	/**
	 * 毛重（kg）
	 */
	private String gw;
	/**
	 * 体积（m³）
	 */
	private String meas;
	/**
	 * 总件数
	 */
	private String totalNumber;
	/**
	 * 运费
	 */
	private BigDecimal freightCharges;
	/**
	 * 运费吨
	 */
	private String revenueTons;
	/**
	 * 运费率
	 */
	private BigDecimal freghtRate;
	/**
	 * 计费单位
	 */
	private String per;
	/**
	 * 运费预付
	 */
	private BigDecimal prepaid;
	/**
	 * 运费到付
	 */
	private BigDecimal freightCollect;
	/**
	 * 预付地点
	 */
	private String prepaidAt;
	/**
	 * 到付地点
	 */
	private String payableAt;
	/**
	 * 出单地点和时间
	 */
	private String placeDate;
	/**
	 * 预付总金额
	 */
	private BigDecimal totalPrepaid;
	/**
	 * 正本提单的份数
	 */
	private Integer numberOriginalBl;
	/**
	 * 装船日期
	 */
	private String loadDate;
	/**
	 * 船名
	 */
	private String loadingBoardVessel;
	/**
	 * 承运人签章
	 */
	private String signedCarrier;
	/**
	 * 海运提单代码
	 */
	private String seaBolCode;
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
