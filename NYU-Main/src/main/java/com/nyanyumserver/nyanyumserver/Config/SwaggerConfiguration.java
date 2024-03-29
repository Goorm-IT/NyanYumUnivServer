package com.nyanyumserver.nyanyumserver.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

@Configuration
@EnableSwagger2
public class SwaggerConfiguration {
    @Bean
    public Docket swaggerApi() {
        return new Docket(DocumentationType.OAS_30)
                .groupName("NYU-MAIN")
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.nyanyumserver.nyanyumserver"))   // 현재 RequestMapping으로 할당된 모든 URL 리스트 추출
                .paths(PathSelectors.any())            // PathSelectorys.any("/api/**")) 와 같이 /api/** 인 URL로만 필터링 할 수 있습니다.
                .build();
    }
    private ApiInfo apiInfo(){
        return new ApiInfoBuilder()
                .title("NYU API")
                .description("USER")
                .version("1.0.0")
                .build();
    }

    private Set<String> getConsumeContentTypes() {
        Set<String> consumes = new HashSet<>();
        consumes.add("application/json");
        return consumes;
    }

    private Set<String> getProduceContentTypes() {
        Set<String> produces = new HashSet<>();
        produces.add("application/json");
        return produces;
    }
}
