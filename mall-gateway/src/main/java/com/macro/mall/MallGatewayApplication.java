package com.macro.mall;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @Author Zhangnana
 * @DATE 2020/12/12 23:29
 * @Version 1.0
 */
@EnableDiscoveryClient
@SpringBootApplication
public class MallGatewayApplication {
    public static void main(String[] args) {
        SpringApplication.run(MallGatewayApplication.class,args);
    }
}
