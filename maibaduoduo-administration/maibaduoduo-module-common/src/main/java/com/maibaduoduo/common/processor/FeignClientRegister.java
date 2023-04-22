package com.maibaduoduo.common.processor;

import com.maibaduoduo.common.annotation.FeignApi;
import com.maibaduoduo.common.utils.PackageTools;
import feign.Feign;
import feign.Request;
import feign.Retryer;
import feign.jackson.JacksonDecoder;
import feign.jackson.JacksonEncoder;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.stereotype.Component;
import java.util.Set;

@Component
public class FeignClientRegister implements BeanFactoryPostProcessor {
    @Value("${feign.basePackage}")
    public String basePackage;

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory configurableListableBeanFactory) throws BeansException {
        Set<String> classes = PackageTools.findPackageClass(basePackage);
        if (classes == null) {
            return;
        }
        Feign.Builder builder = getFeignBuilder();
        if (classes.size() > 0) {
            for (String claz : classes) {
                Class<?> targetClass = null;
                try {
                    targetClass = Class.forName(claz);
                    //url-配置如：http://ip:port/path.
                    Object target = builder.target(targetClass, targetClass.getAnnotation(FeignApi.class).serviceUrl());
                    configurableListableBeanFactory.registerSingleton(targetClass.getName(), target);
                } catch (Exception e) {
                    throw new RuntimeException(e.getMessage());
                }
            }
        }
    }
    public Feign.Builder getFeignBuilder() {
        Feign.Builder builder = Feign.builder()
                .encoder(new JacksonEncoder())//编码
                .decoder(new JacksonDecoder())//解码
                .options(new Request.Options(5000, 5000))//超时设置
                .retryer(new Retryer.Default(5000, 5000, 3));//重试设置
        return builder;
    }

}