package com.zcf.world.common.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 跨域处理
 */
@Configuration
public class CrossConfig implements WebMvcConfigurer {
    @Bean
    public CorsFilter corsFilter() {
        //1.添加CORS配置信息
        final CorsConfiguration config=new CorsConfiguration();
        //1) 允许的域，不要写* 否则cookie 就无法使用了
        config.addAllowedOrigin("*");
        //2) 是否发送cookie信息
        config.setAllowCredentials(true);
        //3) 允许的请求的方式
        config.addAllowedMethod("HEAD");
        config.addAllowedMethod("OPTIONS");
        config.addAllowedMethod("GET");
        config.addAllowedMethod("PUT");
        config.addAllowedMethod("POST");
        config.addAllowedMethod("DELETE");
        config.addAllowedMethod("PATCH");
        //4) 允许的头部信息
        config.addAllowedHeader("*");
        //5）有效的时长
        config.setMaxAge(3600L);
        //2.添加映射路径，拦截一切请求
        UrlBasedCorsConfigurationSource configSource=new UrlBasedCorsConfigurationSource();
        configSource.registerCorsConfiguration("/**", config);
        return new CorsFilter(configSource);

    }
}
