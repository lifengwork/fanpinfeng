package com.maibaduoduo.common.datasources;

import com.alibaba.druid.pool.DruidDataSource;
import com.google.common.collect.Lists;
import com.maibaduoduo.common.utils.SpringContextHolder;

import java.util.List;

public class DataSources {
    public static List<DruidDataSource> sources =Lists.newArrayList(
            (DruidDataSource)SpringContextHolder.getBean("commonmaster"),
            (DruidDataSource)SpringContextHolder.getBean("commonslave"));
};
