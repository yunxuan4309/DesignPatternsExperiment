package com.coffee;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Spring Boot 启动类
 * 
 * 设计说明：
 * 这是整个应用的入口点，启动Spring Boot应用。
 */
@SpringBootApplication
public class CoffeeShopApplication {
    
    public static void main(String[] args) {
        SpringApplication.run(CoffeeShopApplication.class, args);
        System.out.println("========================================");
        System.out.println("☕ 咖啡订购系统启动成功！");
        System.out.println("访问地址: http://localhost:8080");
        System.out.println("========================================");
    }
}
