package com.xxl.job.saas.dao;

import com.baomidou.mybatisplus.annotation.SqlParser;
import com.xxl.job.saas.core.model.XxlJobGroup;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by xuxueli on 16/9/30.
 */
@Mapper
@SqlParser(filter = true)
public interface XxlJobGroupDao {

    List<XxlJobGroup> findAll();

    List<XxlJobGroup> findByAddressType(@Param("addressType") Integer addressType);

    int save(XxlJobGroup xxlJobGroup);

    int update(XxlJobGroup xxlJobGroup);

    int remove(@Param("id") Integer id);

    XxlJobGroup load(@Param("id") Integer id);

    XxlJobGroup getByName(@Param("appName") String appName);

}
