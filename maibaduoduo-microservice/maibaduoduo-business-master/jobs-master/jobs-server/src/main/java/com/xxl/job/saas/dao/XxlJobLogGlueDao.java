package com.xxl.job.saas.dao;

import com.baomidou.mybatisplus.annotation.SqlParser;
import com.xxl.job.saas.core.model.XxlJobLogGlue;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * job log for glue
 *
 * @author xuxueli 2016-5-19 18:04:56
 */
@SqlParser(filter = true)
@Mapper
public interface XxlJobLogGlueDao {


    public int save(XxlJobLogGlue xxlJobLogGlue);

    public List<XxlJobLogGlue> findByJobId(@Param("jobId") Integer jobId);

    public int removeOld(@Param("jobId") Integer jobId, @Param("limit") Integer limit);

    public int deleteByJobId(@Param("jobId") Integer jobId);

}
