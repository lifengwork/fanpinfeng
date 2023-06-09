/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.maibaduoduo.base.utils.excel.fieldtype;

import org.apache.commons.lang3.StringUtils;

import java.text.DecimalFormat;
import java.text.NumberFormat;


/**
 * 金额类型转换（保留两位）
 * @author ThinkGem
 * @version 2020-3-5
 * @example fieldType = MoneyType.class
 */
public class MoneyType implements FieldType {
	
	private NumberFormat nf = new DecimalFormat(",##0.00");

	/**
	 * 获取对象值（导入）
	 */
	public Object getValue(String val) {
		return val == null ? StringUtils.EMPTY : StringUtils.replace(val, ",", StringUtils.EMPTY);
	}

	/**
	 * 获取对象值（导出）
	 */
	public String setValue(Object val) {
		return val == null ? StringUtils.EMPTY : nf.format(val);
	}
	
	/**
	 * 获取对象值格式（导出）
	 */
	public String getDataFormat() {
		return "0.00";
	}
	
}
