
package com.maibaduoduo.web.sys.service;

import java.util.List;

import com.maibaduoduo.base.base.CrudService;
import com.maibaduoduo.base.utils.DictUtils;
import com.maibaduoduo.common.datasources.DS;
import com.maibaduoduo.common.sys.dao.DictDao;
import com.maibaduoduo.common.sys.entity.Dict;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 字典Service
 * @author ThinkGem
 * @version 2014-05-16
 */
@Service
@Transactional(readOnly = true)
public class DictService extends CrudService<DictDao, Dict> {
	
	/**
	 * 查询字段类型列表
	 * @return
	 */
	public List<String> findTypeList(){
		return dao.findTypeList(new Dict());
	}

	/**
	 * @Cacheable value为我们自定义缓存的name，key的属性是缓存的key
	 * @param dict
	 */
	@CachePut(key = DictUtils.CACHE_DICT_MAP)
	@Transactional(readOnly = false)
	@DS
	public void save(Dict dict) {
		super.save(dict);
	}

	@CacheEvict(key=DictUtils.CACHE_DICT_MAP)
	@Transactional(readOnly = false)
	public void delete(Dict dict) {
		super.delete(dict);
	}

}
