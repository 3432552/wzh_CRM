package com.wzh;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.jmx.export.annotation.ManagedAttribute;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@MapperScan("com.wzh.dao")
@EnableTransactionManagement
@ServletComponentScan
public class CrmApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(CrmApplication.class, args);
    }
    //继承   SpringBootServletInitializer是为了打war包
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(CrmApplication.class);
    }
}
