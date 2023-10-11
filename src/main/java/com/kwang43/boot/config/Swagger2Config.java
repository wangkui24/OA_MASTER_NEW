package com.kwang43.boot.config;

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
 * 功能：
 * 作者：kwang43
 * 日期：2023/10/11 16:08
 */

// 1️⃣. 通过@Configuration注解,使得该类成为配置类,Spring可以加载该配置；
// 2️⃣. 再通过@EnableSwagger2注解来启用Swagger2；
// 3️⃣. 通过createRestApi()方法创建Docket的Bean之后,apiInfo()方法用来创建该Api的基本信息(这些基本信息会展现在文档页面中)。select()方法返回一ApiSelectorBuilder实例，用来控制哪些接口暴露给 Swagger来展现。
// 4️⃣. 为了增强用户友好性,我们通常需要自己增加一些说明来丰富文档内容。如下所示，我们通过@ApiOperation注解来给API增加说明、通过@ApiImplicitParams、@ApiImplicitParam注解来给参数增加说明。


@Configuration
@EnableSwagger2
public class Swagger2Config {
    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.any())
                .apis(RequestHandlerSelectors.basePackage("com.kwang43.boot.controller"))
                //.paths(PathSelectors.any())
                .build()
                .apiInfo(apiInfo());
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("接口文档")
                .description("接口测试")
                .version("0.0.1")
                .contact(new Contact("kwang43","https://www.kwang43.com","kui.wang@dxc.com"))
                .termsOfServiceUrl("")
                .license("")
                .licenseUrl("")
                .build();
    }
}