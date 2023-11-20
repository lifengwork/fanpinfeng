/*
 * Copyright (c) 2019-2023 SAAS开源 All rights reserved. lifengwork@yeah.net
 * SAAS系统设计研发交流
 * https://www.maibaduoduo.com
 */
package com.maibaduoduo.financial.statements.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.math.BigDecimal;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 应收账款表
 * 
 * @author saas
 * @email lifengwork@yeah.net
 * @date 2023-11-20 10:53:45
 */
@Data
@TableName("saas_accounts_receivable")
public class AccountsReceivableEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 应收账款ID
	 */
	@TableId
	private Long id;
	/**
	 * 应收账款代码
	 */
	private String accountsReceivableCode;
	/**
	 * 销售日期
	 */
	private Date accountsReceivableDate;
	/**
	 * 采购方编码
	 */
	private String accountsReceivablePurchaseId;
	/**
	 * 供应商编码
	 */
	private String accountsReceivableSupplyId;
	/**
	 * 发票号码
	 */
	private String invoiceNumber;
	/**
	 * 产品/服务描述
	 */
	private String accountsReceivableDesc;
	/**
	 * 交易金额
	 */
	private BigDecimal transactionAmount;
	/**
	 * 付款期限
	 */
	private Integer creditPeriod;
	/**
	 * 已收款项
	 */
	private BigDecimal amountReceived;
	/**
	 * 未收款项
	 */
	private BigDecimal amountNreceived;
	/**
	 * 备注
	 */
	private String remark;
	/**
	 * 采购方地址
	 */
	private String purchaseAddr;
	/**
	 * 采购方电话
	 */
	private String purchasePhone;
	/**
	 * 采购方邮箱
	 */
	private String purchaseEmail;
	/**
	 * 采购方付款银行
	 */
	private String purchaseBank;
	/**
	 * 采购方付款账号
	 */
	private String purchaseAccount;
	/**
	 * 是否预期
	 */
	private Integer overdue;
	/**
	 * 催收时间
	 */
	private Date collectionTime;
	/**
	 * 催收方式（1电话、2邮件、3信函）
	 */
	private Integer collectionMethod;
	/**
	 * 催收内容(同意等)
	 */
	private String collectionContent;
	/**
	 * 催收反馈
	 */
	private String purchaseFeedback;
	/**
	 * 下次催收时间
	 */
	private Date nextCollectionTime;
	/**
	 * 审批人
	 */
	private String approver;
	/**
	 * 审批时间
	 */
	private Date approveTime;
	/**
	 * 审批备注
	 */
	private String approveRemark;
	/**
	 * 应收账款状态
	 */
	private Integer accountsReceivableStatus;
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
