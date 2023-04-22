package com.maibaduoduo.web.utils;

import com.maibaduoduo.common.utils.SpringContextHolder;
import org.apache.ibatis.mapping.ResultMap;
import org.apache.ibatis.mapping.ResultMapping;
import org.apache.ibatis.session.Configuration;
import org.mybatis.spring.SqlSessionFactoryBean;

import java.util.*;
import java.util.stream.Collectors;

public class ResultMapUtils {
    private static ResultMap getBaseResultMap(Class<?> clazz) throws Exception {
        SqlSessionFactoryBean sqlSessionFactoryBean = SpringContextHolder.getBean(SqlSessionFactoryBean.class);
        assert sqlSessionFactoryBean != null;
        Configuration configuration =sqlSessionFactoryBean.getObject().getConfiguration();
        Collection<String> resultMapNames = configuration.getResultMapNames();
        List<ResultMap> resultMaps = resultMapNames.parallelStream()
                .filter(name -> name.contains("."))
                .map(configuration::getResultMap)
                .filter(resultMap -> Objects.equals(resultMap.getType(), clazz))
                .sorted(Comparator.comparing(resultMap -> resultMap.getPropertyResultMappings().size()))
                .collect(Collectors.toList());
        Collections.reverse(resultMaps);
        if (!resultMaps.isEmpty()) {
            for (ResultMap resultMap : resultMaps) {
                Class<?> type = resultMap.getType();
                if (Objects.equals(type, clazz)) {
                    return resultMap;
                }
            }
        }
        return null;
    }

    public static String property2Field(String property, Class<?> clazz) throws Exception {
        ResultMap resultMap = getBaseResultMap(clazz);
        if (null!=resultMap) {
            for (ResultMapping resultMapping : resultMap.getPropertyResultMappings()) {
                if (resultMapping.getProperty().equals(property)) {
                    property = resultMapping.getColumn();
                    return property;
                }
            }
        }
        return null;
    }

}
