package com.example.SpringBootMybatisPlus.config;

import com.github.xiaoymin.knife4j.spring.annotations.EnableKnife4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import springfox.documentation.swagger2.annotations.EnableSwagger2WebMvc;

import java.util.ArrayList;
import java.util.List;

/**
 * swagger文档
 */
@Configuration
@EnableKnife4j
@EnableSwagger2 //开启Swagger
public class SwaggerConfig {
/*    @Bean
    public Docket docketB(){
        return new Docket(DocumentationType.SWAGGER_2).groupName("B");
    }
    @Bean
    public Docket docketC(){
        return new Docket(DocumentationType.SWAGGER_2).groupName("C");
    }
    @Bean
    public Docket docketD(){
        return new Docket(DocumentationType.SWAGGER_2).groupName("D");
    }*/
    @Bean
    public Docket docket(Environment environment){
        // Profiles profile= Profiles.of("dev");
        //通过environment.acceptsProfiles判断是否处在自己设定的环境当中获
        //  boolean flag=environment.acceptsProfiles(profile);
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .groupName("舒钰葳")
                .enable(true)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.example.SpringBootMybatisPlus.controller"))
                // .paths(PathSelectors.ant("/config/**"))
                .build()
                /* 设置安全模式，swagger可以设置访问token */
                .securitySchemes(securitySchemes())
                .securityContexts(securityContexts())
                .pathMapping("/");
        //paths(PathSelectors.ant(路径))过滤
        //paths(PathSelectors.ant("/config/**"))只扫描config下面的包
    }

    //配置Swagger信息apiInfo()
    private ApiInfo apiInfo(){
        //作者信息
        Contact contact = new Contact("舒钰葳", "https://www.baidu.com", "m13693462718.163.com");
        return new ApiInfo(
                "舒钰葳的Swagger文档",
                "后台管理系统",
                "v1.0",
                "https://www.baidu.com",
                contact,
                "Apache 2.0",
                "http://www.apache.org/licenses/LICENSE-2.0",
                new ArrayList()
        );
    }

    /**
     * 安全模式，这里指定token通过Authorization头请求头传递
     */
    private List<SecurityScheme> securitySchemes() {
        List<SecurityScheme> apiKeyList = new ArrayList<SecurityScheme>();
        apiKeyList.add(new ApiKey("Authorization", "X-Token", "header"));
        return apiKeyList;
    }

    /**
     * 安全上下文
     */
    private List<SecurityContext> securityContexts() {
        List<SecurityContext> securityContexts = new ArrayList<>();
        securityContexts.add(
                SecurityContext.builder()
                        .securityReferences(defaultAuth())
                        .build());
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

