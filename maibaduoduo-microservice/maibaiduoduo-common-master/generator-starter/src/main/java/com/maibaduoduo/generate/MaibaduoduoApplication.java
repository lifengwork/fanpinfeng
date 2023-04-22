package com.maibaduoduo.generate;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.maibaduoduo.generate.dao")
public class MaibaduoduoApplication {

	public static void main(String[] args) {
		SpringApplication.run(MaibaduoduoApplication.class, args);
	}
}
