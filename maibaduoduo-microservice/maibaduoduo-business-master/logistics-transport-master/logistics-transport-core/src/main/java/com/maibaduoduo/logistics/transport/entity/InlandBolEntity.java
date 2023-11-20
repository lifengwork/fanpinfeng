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
 * 
 * 陆运提单
 * @author saas
 * @email lifengwork@yeah.net
 * @date 2023-11-20 10:53:45
 */
@Data
@TableName("saas_inland_bol")
public class InlandBolEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 陆运提单ID
	 */
	@TableId
	private Long id;
	/**
	 * 陆运提单代码
	 */
	private String inlandBolCode;
	/**
	 * 收货人
	 */
	private String consingn;
	/**
	 * 合同号
	 */
	private String contractNo;
	/**
	 * 信用证号
	 */
	private String lcNo;
	/**
	 * 通知方
	 */
	private String notify;
	/**
	 * 装运日期
	 */
	private Date shipmentDate;
	/**
	 * 车号
	 */
	private String truckNo;
	/**
	 * 发至
	 */
	private String from;
	/**
	 * 经由
	 */
	private String via;
	/**
	 * 运至
	 */
	private String to;
	/**
	 * 唛头及号码
	 */
	private String marksNos;
	/**
	 * 件数及包装
	 */
	private String numberKindPkgs;
	/**
	 * 货名
	 */
	private String descriptionGoods;
	/**
	 * 毛重（kg）
	 */
	private String gw;
	/**
	 * 尺码（m³）
	 */
	private String measurement;
	/**
	 * 合计
	 */
	private String total;
	/**
	 * 合计件数
	 */
	private Integer totalPkgs;
	/**
	 * 运费乞讨地点
	 */
	private String freightPayableAt;
	/**
	 * 请向下地点接洽提货
	 */
	private String forDeliveryApplyTo;
	/**
	 * 正本份数
	 */
	private String numberOriginal;
	/**
	 * 发货盖章
	 */
	private String shipperEndorsement;
	/**
	 * 收货人签收
	 */
	private String consigneeSignature;
	/**
	 * 签单地点
	 */
	private String place;
	/**
	 * 签单日期
	 */
	private Date issueDate;
	/**
	 * 签单状态
	 */
	private String airwayBolStatus;
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
