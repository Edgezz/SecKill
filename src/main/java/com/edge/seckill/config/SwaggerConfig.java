package com.edge.seckill.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author： Edge
 * @date： 2020/2/26
 * @description： TODO
 * @modifiedBy：
 * @version: 1.0
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {
    //创建文档说明
    public ApiInfo createAI(){
        ApiInfo apiInfo=new ApiInfoBuilder().title("基于SpringBoot实现的秒杀数据接口").
                description("基于SpringBoot实现的秒杀一套后端接口").
                contact(new Contact("Edge","","Edgezz163@163.com")).build();
        return apiInfo;
    }
    //创建Swagger扫描信息
    @Bean
    public Docket createD(){
        return new Docket(DocumentationType.SWAGGER_2).apiInfo(createAI()).select().
                apis(RequestHandlerSelectors.basePackage("com.edge.seckill.controller")).build();
    }
}
