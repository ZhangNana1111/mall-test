package com.macro.mall.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.reactive.CorsWebFilter;
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;
import org.springframework.web.util.pattern.PathPatternParser;

/**
 * 全局跨域配置
 * @Author Zhangnana
 * @DATE 2020/12/13 9:22
 * @Version 1.0
 */
@Configuration
public class GlobalCorsConfig {

    @Bean
    public CorsWebFilter corsWebFilter(){
        //具体封装跨域配置信息的pojo
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.addAllowedMethod("*");
        configuration.addAllowedOrigin("*");
        configuration.addAllowedHeader("*");
        configuration.setAllowCredentials(true);
        // 存储request与跨域配置信息的容器
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource(new PathPatternParser());
        source.registerCorsConfiguration("/**", configuration);
        return new CorsWebFilter(source);
    }
}
