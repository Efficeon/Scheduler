package com.gtedx.util;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
        * Java config for Springfox swagger documentation plugin
        *
        * @author Leonid Dubravsky
        *
        */

@Configuration
@EnableSwagger2
@ComponentScan(basePackages="com.gtedx.controllers")
public class ApplicationSwaggerConfig {
    @Bean
    public Docket customDocket(){
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any())
                .build()
                .apiInfo(getApiInfo());
    }

    private ApiInfo getApiInfo() {
        return new ApiInfo(
                "REST Scheduler backend Api Documentation",
                "This is REST API documentation of the Spring Scheduler backend",
                "1.0",
                "Scheduler backend terms of service",
                new Contact(
                        "Leonid Dubravsky",
                        " ",
                        "ldubravskiy@gmail.com"),
                "Apache 2.0",
                "http://www.apache.org/licenses/LICENSE-2.0");
    }
}
