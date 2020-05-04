package com.sixteen.school;


import com.github.xiaoymin.knife4j.spring.annotations.EnableKnife4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import springfox.bean.validators.configuration.BeanValidatorPluginsConfiguration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;

@Configuration // 声明为配置文件，让spring加载
@EnableSwagger2 // 支持swagger2插件配置
@EnableKnife4j
@Import(BeanValidatorPluginsConfiguration.class)
public class Swagger2Config {
    // apiInfo对象主要是设置我们api文档的标题，描述，访问的地址，创建者等信息
    @SuppressWarnings("deprecation")
    @Bean
    public ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("Api接口文档")
                .description("快速进行Api接口调试")
                .termsOfServiceUrl("127.0.0.1:18080")
                .contact("sixteen")
                .version("1.0")
                .build();
    }

    /**
     * 创建API
     */
    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                // .pathMapping("/dev-api")
                // 用来创建该API的基本信息，展示在文档的页面中（自定义展示的信息）
                .apiInfo(apiInfo())
                // 设置哪些接口暴露给Swagger展示
                .select()
                // 扫描所有有注解的api，用这种方式更灵活
                // .apis(RequestHandlerSelectors.withMethodAnnotation(ApiOperation.class))
                // 扫描指定包中的swagger注解
                .apis(RequestHandlerSelectors.basePackage("com.sixteen.school"))
                // 扫描所有 .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any()).build()
                /* 设置安全模式，swagger可以设置访问token */
                .securitySchemes(securitySchemes()).securityContexts(securityContexts());
    }

    /**
     * 安全模式，这里指定token通过Authorization头请求头传递
     */
    private List<ApiKey> securitySchemes() {
        List<ApiKey> apiKeyList = new ArrayList<ApiKey>();
        apiKeyList.add(new ApiKey("Authorization", "Authorization", "header"));
        return apiKeyList;
    }

    /**
     * 安全上下文
     */
    private List<SecurityContext> securityContexts() {
        List<SecurityContext> securityContexts = new ArrayList<>();
        securityContexts.add(SecurityContext.builder().securityReferences(defaultAuth())
                .forPaths(PathSelectors.regex("^(?!auth).*$")).build());
        return securityContexts;
    }

    /**
     * 默认的安全上引用
     */
    private List<SecurityReference> defaultAuth() {
        AuthorizationScope authorizationScope = new AuthorizationScope("global", "accessEverything");
        AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
        authorizationScopes[0] = authorizationScope;
        List<SecurityReference> securityReferences = new ArrayList<>();
        securityReferences.add(new SecurityReference("Authorization", authorizationScopes));
        return securityReferences;
    }

}
