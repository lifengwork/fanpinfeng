package com.fanpinfeng.test;

import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;

/**
 * @Description: //TODO
 * @date: 2023/4/18 13:30
 * @Author: pm2022
 */
public class TestFile {
    public static void main(String[] args) throws IOException {
        String path = "classpath:initsql/db.sql";
        File dbfile = null;
        String sql = "";
        FileReader fileReader = null;
        try {
            dbfile = ResourceUtils.getFile(path);
           fileReader = new FileReader(dbfile);
            char[] fileChar = new char[(int) dbfile.length()];
            fileReader.read(fileChar);
            sql = new String(fileChar);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (null != fileReader) {
                fileReader.close();
            }
        }
    }
}
