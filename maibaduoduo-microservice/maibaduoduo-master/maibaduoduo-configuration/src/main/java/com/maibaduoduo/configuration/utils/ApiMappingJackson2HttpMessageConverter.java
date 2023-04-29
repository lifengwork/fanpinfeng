/**
 * Copyright (c) 2019-2023 SAAS开源 All rights reserved.
 *
 * SAAS系统设计研发交流
 *
 * https://www.maibaduoduo.com
 */
package com.maibaduoduo.configuration.utils;

import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description: //TODO
 * @date: 2023/4/14 12:57
 * @Author: pm2022
 */
public class ApiMappingJackson2HttpMessageConverter extends MappingJackson2HttpMessageConverter {
    public ApiMappingJackson2HttpMessageConverter() {
        List<MediaType> mediaTypes = new ArrayList<>();
        mediaTypes.add(MediaType.TEXT_PLAIN);
        mediaTypes.add(MediaType.TEXT_HTML);  //加入text/html类型的支持
        mediaTypes.add(MediaType.APPLICATION_OCTET_STREAM);
        setSupportedMediaTypes(mediaTypes);// tag6
    }
}
