package com.maibaduoduo.web.sys.service;

import com.maibaduoduo.base.base.CrudService;
import com.maibaduoduo.common.entity.Page;
import com.maibaduoduo.common.sys.dao.LogDao;
import com.maibaduoduo.common.sys.entity.Log;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.maibaduoduo.common.utils.DateUtils;

/**
 * 日志Service
 * @author ThinkGem
 * @version 2014-05-16
 */
@Service
@Transactional(readOnly = true)
public class LogService extends CrudService<LogDao, Log> {

	public Page<Log> findPage(Page<Log> page, Log log) {
		
		// 设置默认时间范围，默认当前月
		if (log.getBeginDate() == null){
			log.setBeginDate(DateUtils.setDays(DateUtils.parseDate(DateUtils.getDate()), 1));
		}
		if (log.getEndDate() == null){
			log.setEndDate(DateUtils.addMonths(log.getBeginDate(), 1));
		}
		
		return super.findPage(page, log);
		
	}
	
}
