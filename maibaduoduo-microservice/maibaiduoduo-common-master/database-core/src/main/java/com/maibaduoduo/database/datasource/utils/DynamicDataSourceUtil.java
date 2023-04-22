package com.maibaduoduo.database.datasource.utils;

public class DynamicDataSourceUtil {
    public static boolean isOk(Boolean one, Boolean two) {
        return one && two;
    }

    public static boolean isOk(Boolean... one) {
        return one[0] && one[1] && one[2];
    }
}
