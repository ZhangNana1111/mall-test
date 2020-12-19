package com.macro.mall.filter;

import cn.hutool.core.util.StrUtil;
import com.macro.mall.common.constant.AuthConstant;
import com.nimbusds.jose.JWSObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.text.ParseException;

/**
 * 将登录用户的jwt转化成用户信息的全局过滤器
 * @Author Zhangnana
 * @DATE 2020/12/13 0:20
 * @Version 1.0
 */
@Component
public class AuthGlobalFilter implements GlobalFilter, Ordered {
    //Ordered这个接口，来处理相同接口实现类的优先级问题。拦截器的顺序
    private final static Logger LOGGER = LoggerFactory.getLogger(AuthGlobalFilter.class);

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        //ServerWebExchange 相当于当前请求和响应的上下文，ServerWebExchange实例不单存储了Request和Response对象，还提供了一些扩展方法
        //ServerWebExchange命名为服务网络交换器，存放着重要的请求-响应属性、请求实例和响应实例等等，有点像Context的角色
        String token = exchange.getRequest().getHeaders().getFirst(AuthConstant.JWT_TOKEN_HEADER);
        if(StrUtil.isEmpty(token)){
            return chain.filter(exchange);
        }
        try {
            String replaceToken = token.replace(AuthConstant.JWT_TOKEN_PREFIX, "");
            //从token中解析JWS对象
            JWSObject jwsObject = JWSObject.parse(replaceToken);
            String userStr = jwsObject.getPayload().toString();
            LOGGER.info("AuthGlobalFilter.filter() user{}", userStr);
            ServerHttpRequest request = exchange.getRequest().mutate().header(AuthConstant.USER_TOKEN_HEADER, userStr).build();
            exchange = exchange.mutate().request(request).build();
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return chain.filter(exchange);
    }

    @Override
    public int getOrder() {
        return 0;
    }
}
