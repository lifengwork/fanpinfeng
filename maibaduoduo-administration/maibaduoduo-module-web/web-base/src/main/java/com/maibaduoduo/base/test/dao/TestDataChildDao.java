package com.maibaduoduo.base.test.dao;

import com.maibaduoduo.base.test.entity.TestDataChild;
import com.maibaduoduo.common.annotation.CrudDao;
import com.maibaduoduo.common.annotation.MyBatisDao;

/**
 * 主子表生成DAO接口
 * @author ThinkGem
 * @version 2015-04-06
 */
@MyBatisDao
public interface TestDataChildDao extends CrudDao<TestDataChild> {
	
}