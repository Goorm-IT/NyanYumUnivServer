package com.nyanyumserver.nyanyumserver.Controller;

import io.swagger.annotations.ApiOperation;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


@RestController
@EnableRedisHttpSession
@EnableSwagger2
public class MainController {

    @GetMapping("/main/banner")
    @ApiOperation(value = "메인배너")
    public void getMainBanner(){

    }
}

