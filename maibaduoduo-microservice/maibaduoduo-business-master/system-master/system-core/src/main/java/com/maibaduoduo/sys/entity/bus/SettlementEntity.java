package com.maibaduoduo.sys.entity.bus;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.math.BigDecimal;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 
 * 
 * @author lf
 * @email lifengwork@yeah.net
 * @date 2020-10-23 17:25:09
 */
@Data
@TableName("tb_settlement")
public class SettlementEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId
	private String id;
	/**
	 * 
	 */
	private String orderId;
	/**
	 * 
	 */
	private BigDecimal orderActualAmount;
	/**
	 * 
	 */
	private BigDecimal agentDivideAmount;
	/**
	 * 
	 */
	private BigDecimal agentTwoDivideAmount;
	/**
	 * 
	 */
	private BigDecimal agentThreeDivideAmount;
	/**
	 * 
	 */
	private BigDecimal platformDivideAmount;
	/**
	 * 
	 */
	private Date settlemetDate;
	/**
	 * 0无效1有效
	 */
	private Integer status;
	/**
	 * 创建日期
	 */
	private Date created;
	/**
	 * 创建人
	 */
	private String createdBy;

}
