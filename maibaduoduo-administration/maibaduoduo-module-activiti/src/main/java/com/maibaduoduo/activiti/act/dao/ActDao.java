package com.maibaduoduo.activiti.act.dao;

import com.maibaduoduo.activiti.act.entity.Act;
import com.maibaduoduo.common.annotation.CrudDao;
import com.maibaduoduo.common.annotation.MyBatisDao;

/**
 * 审批DAO接口
 */
@MyBatisDao
public interface ActDao extends CrudDao<Act> {

	public int updateProcInsIdByBusinessId(Act act);
	
}
