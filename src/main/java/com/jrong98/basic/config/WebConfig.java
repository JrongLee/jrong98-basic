package com.jrong98.basic.config;

import com.jrong98.basic.common.handle.AuthenticateHandleInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Web 配置
 * @author jrong98
 * @date 2022/6/22
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new AuthenticateHandleInterceptor())
            .excludePathPatterns("/auth/**")
            .excludePathPatterns("/error")
            .addPathPatterns("/**")
            .order(1);
    }
}
