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
 * 航运提单
 * @author saas
 * @email lifengwork@yeah.net
 * @date 2023-11-20 10:53:45
 */
@Data
@TableName("saas_airway_bol")
public class AirwayBolEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 航运提单ID
	 */
	@TableId
	private Long id;
	/**
	 * 航运提单代码
	 */
	private String airwayBolCode;
	/**
	 * 指主单号  205-4946 9475
	 */
	private Integer mawbNo;
	/**
	 * 指分单号
	 */
	private String hawbNo;
	/**
	 * 指发货人名称和地址
	 */
	private String shipperNameAddress;
	/**
	 * 指空运单签发人
	 */
	private String airWaybillIssuedBy;
	/**
	 * 指收货人名称和地址
	 */
	private String consigneeNameAddress;
	/**
	 * 指代理人信息
	 */
	private String issuingCarrierAgentNameCity;
	/**
	 * 指货运代理的IATA CASS代码
	 */
	private String agentIataCode;
	/**
	 *  指货运代理的IATA结算账号
	 */
	private String accountNo;
	/**
	 * 指财务结算信息
	 */
	private String accountingInformation;
	/**
	 * 指始发站机场
	 */
	private String airportDeparture;
	/**
	 * 指证明参考编号
	 */
	private String referenceNumber;
	/**
	 * 填写目的站机场三字代码
	 */
	private String to;
	/**
	 * 指第一承运人或者头程承运人，Currency
	 */
	private String byFirstCarrierToByToBy;
	/**
	 * 指币种
	 */
	private String currency;
	/**
	 * 费用付款方式的代号
	 */
	private String chgsCod;
	/**
	 * 航空运费和声明价值附加费付款方式，全称Weight Charge/Valuation Charge。底下PPD（Prepaid）代表全部费用预付，选择此方式则打上“PP”或者打上“X”。COLL（Collect）代表全部费用到付，选择此方式则打上“CC”或者打上“X”。一般情况下主单打PP，分单打CC。
	 */
	private String wtVal;
	/**
	 * 指除了运费以及声明价值附加费以外的其他费用的付款方式，和WT/VAL这点一样，根据实际情况打上“PP”或者“CC”。一般情况下主单打PP，分单打CC。
	 */
	private String other;
	/**
	 * 指提供给承运人的声明价值，填写发货人向航空公司运输货物的声明价值金额；如果发货人的货物没有声明价值，此栏必须打上“N.V.D”（No Value Declared）
	 */
	private String declaredValueCarriage;
	/**
	 * 指提供给海关的声明价值，填写货物进出口通关时所需声明的商业价值金额；如果货物没有商业声明价值，此栏需要打上“N.C.V.”（No Commocial Value），又或者如果以发货人提供的发票价值为准，则此栏打上“AS PER INV.”
	 */
	private String declaredValueCustoms;
	/**
	 * 指目的站机场，需要填写机场全称
	 */
	private String airportDestination;
	/**
	 * 指航班号以及起飞时间，填写货物已经确定的航班号码以及起飞时间，若涉及转飞则加上二程航班号以及时间。我的举例图片上标记的“EK-9845”指的是航班号，“19.NOV.22”指的是具体发运日期
	 */
	private Date flightDate;
	/**
	 * 指保险金额
	 */
	private BigDecimal amountInsurance;
	/**
	 * 处理事项信息
	 */
	private String handlingInformation;
	/**
	 * 指货物的件数和运价组成点
	 */
	private String noPieceRcp;
	/**
	 * 指货物毛重(Kg\lb)
	 */
	private String grossWeight;
	/**
	 * M 代表最低运费Minimum Charge，N代表45公斤以下运价Normal Rate，Q代表45公斤以上运价Quantity Rate，C代表指定商品运价Specific Commodity Rate，U代表集装化设备基本运费或运Unit load Device Basic Charge or Rate。
	 */
	private String rateClass;
	/**
	 * 指商品代号
	 */
	private String commodityItemNo;
	/**
	 * 指货物计费重
	 */
	private String chargeableWeight;
	/**
	 * 指运价/运费
	 */
	private String rateCharge;
	/**
	 * 指运费总金额
	 */
	private BigDecimal totalFreight;
	/**
	 * 指货物品名、数量、尺寸以及体积
	 */
	private String natureQuantityGood;
	/**
	 * 指航空运费
	 */
	private String weightCharge;
	/**
	 * 指声明价值附加费
	 */
	private String valuationCharge;
	/**
	 * 指税费
	 */
	private String tax;
	/**
	 * 指由代理收取的其他费用
	 */
	private String totalOtherChargesDueAgent;
	/**
	 * 指由承运人（航空公司）收取的其他费用
	 */
	private String totalOtherChargesDueCarrier;
	/**
	 * 指预付总计金额/到付总计金额
	 */
	private BigDecimal toalPrepaidTotalCollect;
	/**
	 * 指币种汇率，填写目的地国家的币种和汇率
	 */
	private String currencyConversionRates;
	/**
	 * 指在目的国到付的金额
	 */
	private String ccChargesInDestCurrency;
	/**
	 * 指其他费用 AWC运单费、MYC/FSC燃油附加费、SCC/SC安全附加费、UH集装设备操作费、ADC文件费、TC地面操作费等
	 */
	private BigDecimal otherCharges;
	/**
	 * 指发货人或其代理签名，填写货运代理名称后签字或者盖章。
	 */
	private String signatureShipperHisAgent;
	/**
	 * 指签单时间、地点以及承运人或者代理的签名或盖章。时间按照按日、月、年的顺序填写，地点可以是起运地机场或城市的全称或缩写。
	 */
	private String executedOnDateAtPlaceSignatureIssuingBranch;
	/**
	 * 指目的站机场费用，这一栏由承运人在目的地填写。
	 */
	private String chargesAtDestination;
	/**
	 * 指到付费用总金额
	 */
	private String totalCollectCharges;
	/**
	 * 航运提单状态
	 */
	private Integer airwayBolStatus;
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
