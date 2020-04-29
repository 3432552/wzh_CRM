package com.wzh.config;

import com.wzh.interceptor.LoginInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import javax.annotation.Resource;

/**
 * @Author: wzh
 * @ClassName: InterceptorConfig
 * @Description:  实现WebMvcConfigurer，springboot新版本都这么做
 * @Date: 2020/3/29 18:30
 */
@Configuration
public class InterceptorConfig implements WebMvcConfigurer {
    @Autowired
    private LoginInterceptor loginInterceptor;

    /**
     * 我遇到了一个坑:不拦截的路径不能带上下文的路径:
     * /crm/mes 这样就不行,可以这样 /mes 这里只是web路径
     *
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(loginInterceptor).
                addPathPatterns("/**").excludePathPatterns
                ("/mes", "/gifCode", "login", "/**/*.css", "/**/*.html", "/**/*.js", "/**/*.png", "/**/*.JPG", "/**/*.jpeg", "/**/*.woff");
    }
}
