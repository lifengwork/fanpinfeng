package com.maibaduoduo.base.test.dao;

import com.maibaduoduo.base.test.entity.TestTree;
import com.maibaduoduo.common.annotation.MyBatisDao;
import com.maibaduoduo.common.annotation.TreeDao;

/**
 * 树结构生成DAO接口
 * @author ThinkGem
 * @version 2015-04-06
 */
@MyBatisDao
public interface TestTreeDao extends TreeDao<TestTree> {
	
}