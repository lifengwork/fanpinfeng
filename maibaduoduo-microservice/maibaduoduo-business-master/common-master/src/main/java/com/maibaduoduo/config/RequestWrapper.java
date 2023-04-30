/**
 * Copyright (c) 2019-2023 SAAS开源 All rights reserved.
 *
 * SAAS系统设计研发交流
 *
 * https://www.maibaduoduo.com
 */
package com.maibaduoduo.config;

import javax.servlet.ReadListener;
import javax.servlet.ServletInputStream;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.io.*;
import java.nio.charset.Charset;

/**
 * 请求包装
 */
public class RequestWrapper extends HttpServletRequestWrapper {

    private byte[] body;

    public RequestWrapper(HttpServletRequest request) {
        super(request);
        String bodyStr = getBodyString(request);
        body   = bodyStr.getBytes(Charset.defaultCharset());
    }
    public String getBodyString(ServletRequest request){
        try{
            return inputStreamToString(request.getInputStream());
        }catch(IOException e){
            throw new RuntimeException(e);
        }
    }
    public String getBodyString(){
        final InputStream inputStream = new ByteArrayInputStream(body);
        return inputStreamToString(inputStream);
    }
    public String inputStreamToString(InputStream inputStream){
        StringBuilder stringBuilder = new StringBuilder();
        BufferedReader reader = null;
        try{
            reader = new BufferedReader(new InputStreamReader(inputStream,Charset.defaultCharset()));
            String line;
            while((line=reader.readLine())!=null){
             stringBuilder.append(line);
            }
        }catch(IOException e){
            throw new RuntimeException(e);
        }finally {
            if(reader!=null){
                try {
                    reader.close();
                } catch (IOException e) {

                }
            }
        }
        return  stringBuilder.toString();
    }

    @Override
    public BufferedReader getReader() throws IOException {
        return new BufferedReader(new InputStreamReader(getInputStream()));
    }

    @Override
    public ServletInputStream getInputStream() throws IOException {
        final ByteArrayInputStream inputStream = new ByteArrayInputStream(body);
        return new ServletInputStream() {
            @Override
            public boolean isFinished() {
                return false;
            }

            @Override
            public boolean isReady() {
                return false;
            }

            @Override
            public void setReadListener(ReadListener readListener) {

            }
            @Override
            public int read() throws IOException {
                return inputStream.read();
            }
        };
    }
}
