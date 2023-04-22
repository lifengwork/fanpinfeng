package com.maibaduoduo.common.utils;

import org.springframework.util.ResourceUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

/**
 * 文件下载工具类
 */
public class FileDownLoadUtils {
    /**
     * 文件下载
     */
    public static void  downLoadFile() throws IOException {
        HttpServletResponse response = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getResponse();
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        String fileName = "盘活计划上报模板.xlsx";
        File file = null;
        file = ResourceUtils.getFile("classpath:downfile/盘活计划上报模板.xlsx");
        if (!file.exists()) {
            throw new FileNotFoundException("文件读取失败");
        }
        InputStream inputStream = null;
        ServletOutputStream servletOutputStream = null;
        response.reset();
        response.setCharacterEncoding("utf-8");
        response.setContentType("application/vnd.ms-excel");
        String userAgent = request.getHeader("User-Agent");
        if (userAgent.contains("MSIE") || userAgent.contains("Trident")) {
            fileName = java.net.URLEncoder.encode(fileName, "UTF-8");
        } else {
            fileName = new String(fileName.getBytes("UTF-8"), "ISO-8859-1");
        }
        response.addHeader("Content-Disposition", "attachment;filename=\"" + fileName + "\"");
        int fileLength = (int) file.length();
        response.setContentLength(fileLength);
        try {
            if (fileLength != 0) {
                inputStream = new FileInputStream(file);
                byte[] buf = new byte[4096];
                servletOutputStream = response.getOutputStream();
                int readLength;
                while ((readLength = inputStream.read(buf)) != -1) {
                    servletOutputStream.write(buf, 0, readLength);
                }
            }
        } finally {
            // 关闭输出输入流
            if (servletOutputStream != null) {
                servletOutputStream.flush();
                servletOutputStream.close();
            }
            if (inputStream != null) {
                inputStream.close();
            }
        }
    }
}
