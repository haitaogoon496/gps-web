package com.mljr.gps.interceptor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import javax.print.attribute.standard.Fidelity;

/**
 * @Description
 * @Date：Created in 下午10:40 2018/1/28
 */
@Configuration
public class InterceptorConfig extends WebMvcConfigurerAdapter{
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Value("${spring.profiles.active}")
    private String local;

    @Bean
    HandlerContextInterceptor localInterceptor() {
        return new HandlerContextInterceptor();
    }

    @Bean
    FileUploadInterceptor fileUploadInterceptor() {
        return new FileUploadInterceptor();
    }


    @Override
    public void addInterceptors(InterceptorRegistry  registry) {
        logger.info("当前环境 -- enviroment:{}",local);
        if ("local".equals(local)) {
            return;
        }

        registry.addInterceptor(localInterceptor()).addPathPatterns("/**")
                .excludePathPatterns("/login/**")
                .excludePathPatterns("/healthCheck")
                .excludePathPatterns("/swagger-resources/**").excludePathPatterns("/error/**");
        super.addInterceptors(registry);
    }

}
