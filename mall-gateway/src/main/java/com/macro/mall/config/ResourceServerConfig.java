package com.macro.mall.config;

import cn.hutool.core.util.ArrayUtil;
import com.macro.mall.authorization.AuthorizationManager;
import com.macro.mall.common.constant.AuthConstant;
import com.macro.mall.component.RestAuthenticationEntryPoint;
import com.macro.mall.component.RestfulAccessDeniedHandle;
import com.macro.mall.filter.IgnoreUrlsRemoveJwtFilter;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.SecurityWebFiltersOrder;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;
import org.springframework.security.oauth2.server.resource.authentication.ReactiveJwtAuthenticationConverterAdapter;
import org.springframework.security.web.server.SecurityWebFilterChain;
import reactor.core.publisher.Mono;

/**
 * 资源服务器配置
 * @Author Zhangnana
 * @DATE 2020/12/13 17:01
 * @Version 1.0
 */
@AllArgsConstructor
@Configuration
@EnableWebFluxSecurity
public class ResourceServerConfig {

    private final AuthorizationManager authorizationManager;
    private final IgnoreUrlsConfig ignoreUrlsConfig;
    private final RestAuthenticationEntryPoint restAuthenticationEntryPoint;
    private final RestfulAccessDeniedHandle restfulAccessDeniedHandle;
    private final IgnoreUrlsRemoveJwtFilter ignoreUrlsRemoveJwtFilter;

    public SecurityWebFilterChain springSecurityFilterChain(ServerHttpSecurity httpSecurity){
        httpSecurity.oauth2ResourceServer().jwt()
                .jwtAuthenticationConverter(jwtAuthenticationConverter());
        // 自定义处理jwt请求头过期或者签名错误的结果
        httpSecurity.oauth2ResourceServer().authenticationEntryPoint(restAuthenticationEntryPoint);
        // 对白名单路径，直接移除jwt请求头
        httpSecurity.addFilterBefore(ignoreUrlsRemoveJwtFilter, SecurityWebFiltersOrder.AUTHENTICATION);
        httpSecurity.authorizeExchange()
                .pathMatchers(ArrayUtil.toArray(ignoreUrlsConfig.getUrls(),String.class)).permitAll() // 白名单配置
                .anyExchange().access(authorizationManager) //鉴权管理器配置
                .and().exceptionHandling()
                .accessDeniedHandler(restfulAccessDeniedHandle) // 处理未授权
                .authenticationEntryPoint(restAuthenticationEntryPoint) // 处理未认证
                .and().csrf().disable();
        return httpSecurity.build();
    }

    @Bean
    public Converter<Jwt, ? extends Mono<? extends AbstractAuthenticationToken>> jwtAuthenticationConverter() {
        JwtGrantedAuthoritiesConverter jwtGrantedAuthoritiesConverter = new JwtGrantedAuthoritiesConverter();
        jwtGrantedAuthoritiesConverter.setAuthorityPrefix(AuthConstant.AUTHORITY_PREFIX);
        jwtGrantedAuthoritiesConverter.setAuthoritiesClaimName(AuthConstant.AUTHORITY_CLAIM_NAME);
        JwtAuthenticationConverter jwtAuthenticationConverter = new JwtAuthenticationConverter();
        jwtAuthenticationConverter.setJwtGrantedAuthoritiesConverter(jwtGrantedAuthoritiesConverter);
        return new ReactiveJwtAuthenticationConverterAdapter(jwtAuthenticationConverter);
    }

}
