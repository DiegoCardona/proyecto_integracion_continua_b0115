package com.poli.integracioncontinua;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.poli.integracioncontinua.services.RedisService;

@SpringBootApplication
public class IntegracioncontinuaApplication {

    @Autowired
    private RedisService redisService;

    private String redisValue;

    public static void main(String[] args) {
        SpringApplication.run(IntegracioncontinuaApplication.class, args);
    }

    public String getRedisValue() {
        redisService.set("key", "poligran");
        redisValue = redisService.get("key");
        return redisValue;
    }
}