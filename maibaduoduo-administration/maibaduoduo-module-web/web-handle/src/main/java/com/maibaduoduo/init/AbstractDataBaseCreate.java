/*
 * Copyright (c) 2019-2023 SAAS开源 All rights reserved.
 *
 * SAAS系统设计研发交流
 *
 * https://www.maibaduoduo.com
 */
package com.maibaduoduo.init;

import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.sql.SQLException;

/**
 * @Description: //TODO
 * @date: 2023/4/18 13:42
 * @Author: pm2022
 */
public abstract class AbstractDataBaseCreate implements Create {
    protected String path = "classpath:initsql/db.sql";

    public String buildSql() throws Exception {
        File dbfile = null;
        String sql = "";
        FileInputStream fileInputStream = null;
        InputStreamReader inputStreamReader = null;
        try {
            dbfile = ResourceUtils.getFile(path);
            fileInputStream = new FileInputStream(dbfile);
            inputStreamReader = new InputStreamReader(fileInputStream, "UTF-8");
            char[] fileChar = new char[(int) dbfile.length()];
            inputStreamReader.read(fileChar);
            sql = new String(fileChar);
        } catch (Exception e) {
            throw new Exception(e);
        } finally {
            if (null != inputStreamReader) {
                inputStreamReader.close();
            }
            if (null != fileInputStream) {
                fileInputStream.close();
            }
        }
        return sql;
    }

    protected abstract void initDb(DbType dbType) throws SQLException;
}
