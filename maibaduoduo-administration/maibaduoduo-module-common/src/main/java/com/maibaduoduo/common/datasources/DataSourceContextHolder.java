package com.maibaduoduo.common.datasources;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 当前线程数据源持有
 */
public class DataSourceContextHolder {
    private static Logger log = LoggerFactory.getLogger(DataSourceContextHolder.class);
    public static final String DEFAULT_DS = "master";
    private static final ThreadLocal<String> contextHolder = new InheritableThreadLocal<String>();

    public static void setDB(String dbType) {
        log.info("切换到，{}，数据源",dbType);
        contextHolder.set(dbType);
    }
    public static String getDB() {
        if(contextHolder.get()==null){
            return DEFAULT_DS;
        }else{
            return (contextHolder.get());
        }
    }
    public static void clearDB() {
        contextHolder.remove();
    }
}