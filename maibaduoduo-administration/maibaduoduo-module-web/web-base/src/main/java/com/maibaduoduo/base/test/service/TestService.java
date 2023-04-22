package com.maibaduoduo.base.test.service;

import com.maibaduoduo.base.base.CrudService;
import com.maibaduoduo.base.test.dao.TestDao;
import com.maibaduoduo.base.test.entity.Test;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 测试Service
 * @author ThinkGem
 * @version 2013-10-17
 */
@Service
@Transactional(readOnly = true)
public class TestService extends CrudService<TestDao, Test> {

}
