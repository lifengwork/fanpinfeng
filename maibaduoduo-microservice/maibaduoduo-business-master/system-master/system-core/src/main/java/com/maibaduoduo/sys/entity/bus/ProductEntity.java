package com.maibaduoduo.sys.entity.bus;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

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
@TableName("tb_product")
public class ProductEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId
	private String id;
	/**
	 * 
	 */
	private String name;
	/**
	 * 图片头像可以显示多张（滚动显示）
	 */
	private String imageTitle;
	/**
	 * 商品详情（全部展示图片(图文布局形式））
	 */
	private String imagDedails;
	/**
	 * 商品所在区域（来源于代理商创建商品时的当前区域）
	 */
	private String area;
	/**
	 * 0下架1上架
	 */
	private Integer upOrDown;
	/**
	 * 0禁用1启用
	 */
	private Integer status;
	/**
	 * 
	 */
	private Date created;
	/**
	 * 代理商
	 */
	private String agentid;

}
