package com.maibaduoduo.xss.converter;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.maibaduoduo.xss.utils.XssUtils;
import com.sun.org.slf4j.internal.Logger;
import com.sun.org.slf4j.internal.LoggerFactory;

import java.io.IOException;

/**
 * 基于xss的 json 序列化器
 * 在本项目中，没有使用该类
 *
 * @author zuihou
 * @date 2019/06/28
 */
public class XssStringJsonSerializer extends JsonSerializer<String> {
    private static Logger log = LoggerFactory.getLogger(XssStringJsonSerializer.class);
    @Override
    public Class<String> handledType() {
        return String.class;
    }

    @Override
    public void serialize(String value, JsonGenerator jsonGenerator,
                          SerializerProvider serializerProvider) throws IOException {
        if (value != null) {
            try {
                String encodedValue = XssUtils.xssClean(value, null);
                jsonGenerator.writeString(encodedValue);
            } catch (Exception e) {
                log.error("序列化失败:[{}]", value, e);
            }
        }
    }

}
