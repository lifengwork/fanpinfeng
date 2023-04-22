package com.maibaduoduo.configuration.config;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import feign.FeignException;
import feign.Response;
import feign.Util;
import feign.codec.ErrorDecoder;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.context.annotation.Configuration;

/**
 * Created by Administrator on 2019/11/17 0017.
 */
@Slf4j
@Configuration
public class ExceptionErrorDecoder  implements ErrorDecoder {
    ObjectMapper objectMapper = new ObjectMapper();
    @Override
    public Exception decode(String methodKey, Response response) {
        try {
            if (response.body() != null) {
                ExceptionInfo exceptionInfo = objectMapper.readValue(Util.toString(response.body().asReader()), new TypeReference<ExceptionInfo>() {
                    /**
                     *  TO DO
                     */
                });
                Class clazz = Class.forName(exceptionInfo.getException());
                return (Exception) clazz.getDeclaredConstructor(String.class).newInstance(exceptionInfo.getMessage());
            }

        } catch (Exception e) {
            log.error(ExceptionUtils.getStackTrace(e));
            e.printStackTrace();
        }
        return FeignException.errorStatus(methodKey, response);
    }
}
