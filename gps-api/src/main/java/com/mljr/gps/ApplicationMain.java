package com.mljr.gps;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.PropertySource;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Date;
/**
 * @Description SpringBoot启动入口
 * @Date：Created in 下午10:40 2018/1/28
 */
@SpringBootApplication
@ComponentScan(basePackages = {"com.mljr"})
@PropertySource("classpath:config.properties")
@EnableAspectJAutoProxy(exposeProxy=true)
@EnableSwagger2
public class ApplicationMain extends SpringBootServletInitializer{
    public static void main(String[] args) {
        SpringApplication.run(ApplicationMain.class, args);
        System.out.println("########Spring Boot start time:"+new Date()+"########");
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(ApplicationMain.class);
    }
}
