package com.maibaduoduo.base.db;

import com.maibaduoduo.common.annotation.MyBatisDao;
import org.apache.ibatis.annotations.Param;

/**
 * mysql bzdb mapper
 * 执行创建数据库、表等sql语句，或者代码动态拼接的sql语句
 */
@MyBatisDao
public interface DbMapper {
    /**
     * 执行sql语句
     *
     * @param sql
     * @return
     */
    void  exec(@Param("sql") String sql);
}
