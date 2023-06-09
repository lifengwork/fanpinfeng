package com.xxl.job.saas.dao;

import com.baomidou.mybatisplus.annotation.SqlParser;
import com.xxl.job.saas.core.model.XxlJobLog;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * job log
 *
 * @author xuxueli 2016-1-12 18:03:06
 */
@SqlParser(filter = true)
@Mapper
public interface XxlJobLogDao {

    public List<XxlJobLog> pageList(@Param("offset") Integer offset,
                                    @Param("pagesize") Integer pagesize,
                                    @Param("jobGroup") Integer jobGroup,
                                    @Param("jobId") Integer jobId,
                                    @Param("triggerTimeStart") Date triggerTimeStart,
                                    @Param("triggerTimeEnd") Date triggerTimeEnd,
                                    @Param("logStatus") Integer logStatus);

    public int pageListCount(@Param("offset") Integer offset,
                             @Param("pagesize") Integer pagesize,
                             @Param("jobGroup") Integer jobGroup,
                             @Param("jobId") Integer jobId,
                             @Param("triggerTimeStart") Date triggerTimeStart,
                             @Param("triggerTimeEnd") Date triggerTimeEnd,
                             @Param("logStatus") Integer logStatus);

    public XxlJobLog load(@Param("id") Integer id);

    public int save(XxlJobLog xxlJobLog);

    public int updateTriggerInfo(XxlJobLog xxlJobLog);

    public int updateHandleInfo(XxlJobLog xxlJobLog);

    public int delete(@Param("jobId") Integer jobId);

    public int triggerCountByHandleCode(@Param("handleCode") Integer handleCode);

    public List<Map<String, Object>> triggerCountByDay(@Param("from") Date from,
                                                       @Param("to") Date to);

    public int clearLog(@Param("jobGroup") Integer jobGroup,
                        @Param("jobId") Integer jobId,
                        @Param("clearBeforeTime") Date clearBeforeTime,
                        @Param("clearBeforeNum") Integer clearBeforeNum);

}
