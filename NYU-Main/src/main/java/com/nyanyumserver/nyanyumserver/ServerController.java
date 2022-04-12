package com.nyanyumserver.nyanyumserver;

import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;


//TODO: Session 설계

@EnableAsync
@EnableRedisHttpSession
@EnableEurekaClient
@SpringBootApplication
public class ServerController {

    public static void main(String[] args) {
        SpringApplication.run(ServerController.class, args);
    }


}
