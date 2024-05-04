package com.etc.order.app.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan(basePackages={"com.etc.**.mapper*"})
public class MyBatisConfig {
}