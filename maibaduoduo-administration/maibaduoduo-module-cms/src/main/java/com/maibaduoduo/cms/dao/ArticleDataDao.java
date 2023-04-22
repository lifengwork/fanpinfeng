package com.maibaduoduo.cms.dao;
import com.maibaduoduo.cms.entity.ArticleData;
import com.maibaduoduo.common.annotation.CrudDao;
import com.maibaduoduo.common.annotation.MyBatisDao;

/**
 * 文章DAO接口
 * @author ThinkGem
 * @version 2013-8-23
 */
@MyBatisDao
public interface ArticleDataDao extends CrudDao<ArticleData> {
	
}
