package com.maibaduoduo.cms.service;

import com.maibaduoduo.base.base.CrudService;
import com.maibaduoduo.cms.dao.ArticleDataDao;
import com.maibaduoduo.cms.entity.ArticleData;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 站点Service
 * @author ThinkGem
 * @version 2013-01-15
 */
@Service
@Transactional(readOnly = true)
public class ArticleDataService extends CrudService<ArticleDataDao, ArticleData> {

}
