package com.maibaduoduo.database.datasource.dynamic.properties;

import com.google.common.collect.Lists;

import java.util.List;

/**
 * 数据源统一配置，支持一主一从
 *
 * @Auth maiba
 * @Date 2021-09-5
 */
public class DsContants {
    public static List<String> dsList = Lists.newArrayList(
            "none", "none_slave", "column",
            "column_slave", "datasource", "datasource_slave", "schema", "schema_slave");

    public enum Ds {
        DEFAULT("none"), DEFAULT_SLAVE("none_slave"), COLUMN("column"), COLUMN_SLAVE("column_slave"),
        DATAROURCE("datasource"), DATAROURCE_SLAVE("datasource_slave"), SCHEMA("schema"), SCHEMA_SLAVE("schema_slave");
        public String DSNAME;

        Ds(String dsName) {
            this.DSNAME = dsName;
        }
    }
}
