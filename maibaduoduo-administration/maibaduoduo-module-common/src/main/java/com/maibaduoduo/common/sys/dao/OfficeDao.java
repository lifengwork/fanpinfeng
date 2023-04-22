package com.maibaduoduo.common.sys.dao;

import com.maibaduoduo.common.annotation.MyBatisDao;
import com.maibaduoduo.common.annotation.TreeDao;
import com.maibaduoduo.common.sys.entity.Office;

/**
 * 机构DAO接口
 * @author ThinkGem
 * @version 2014-05-16
 */
@MyBatisDao
public interface OfficeDao extends TreeDao<Office> {
	
}
