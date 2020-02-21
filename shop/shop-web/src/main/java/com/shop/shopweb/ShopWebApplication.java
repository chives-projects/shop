package com.shop.shopweb;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.shop.shopweb.mapper")
public class ShopWebApplication {

    public static void main(String[] args) {
        SpringApplication.run(ShopWebApplication.class, args);
    }

}
