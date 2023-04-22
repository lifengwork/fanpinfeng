package com.maibaduoduo.base.test.dao;
import com.maibaduoduo.base.test.entity.Test;
import com.maibaduoduo.common.annotation.CrudDao;
import com.maibaduoduo.common.annotation.MyBatisDao;


/**
 * 测试DAO接口
 * @author ThinkGem
 * @version 2013-10-17
 */
@MyBatisDao
public interface TestDao extends CrudDao<Test> {
	
}
