package com.macro.mall.auth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @Author Zhangnana
 * @DATE 2020/12/19 21:40
 * @Version 1.0
 */

@SpringBootApplication(scanBasePackages = "com.macro.mall")
@EnableDiscoveryClient
@EnableFeignClients
public class MallAuthApplication {
    public static void main(String[] args) {
        SpringApplication.run(MallAuthApplication.class,args);
    }
}
