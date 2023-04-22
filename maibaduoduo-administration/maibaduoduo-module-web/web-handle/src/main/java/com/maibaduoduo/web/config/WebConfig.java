package com.maibaduoduo.web.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

@Configuration
@EnableWebMvc
public class WebConfig  extends WebMvcConfigurationSupport {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("https://localhost")
                .allowedMethods("*") //允许的http方法(GET,PUT,POST,DELETE...),"*"表示允许所有方法
                .allowedHeaders("Content-Type","Access-Control-Allow-Headers", "x-requested-with","Authorization")
                .allowCredentials(true).maxAge(3600);
    }

}
