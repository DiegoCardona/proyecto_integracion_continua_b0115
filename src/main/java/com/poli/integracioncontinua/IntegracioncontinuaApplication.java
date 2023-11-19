package com.poli.integracioncontinua;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.poli.integracioncontinua.services.RedisService;

@SpringBootApplication
public class IntegracioncontinuaApplication implements CommandLineRunner {

   @Autowired
    private RedisService redisService;

    public static void main(String[] args) {
        SpringApplication.run(IntegracioncontinuaApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        redisService.set("key", "poligran");
        String value = redisService.get("key");
        System.out.println("Valor obtenido desde la base de datos de redisService: " + value);
    }

}
