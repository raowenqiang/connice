package com.connice.common.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springdoc.core.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author: WenQiangRao
 * @Description:
 * @Date: Created in 17:14 2022/10/8
 * Modified By:
 **/
@Configuration
public class SwaggerConfig{

    @Bean
    public GroupedOpenApi userApi(){
        String[] paths = { "/**" };
        String[] packagedToMatch = { "com.connice" };
        return GroupedOpenApi.builder().group("connice")
                .pathsToMatch(paths)
                .packagesToScan(packagedToMatch).build();
    }

    @Bean
    public OpenAPI customOpenAPI() {
        Contact contact= new Contact();
        contact.setName("raoernqiang0624@126.com");

        OpenAPI openapi = new OpenAPI().info(new Info()
                .title("connice")
                .description( "connice")
                .contact(contact)
                .version("1.0")
                .termsOfService("https://maku.net")
                .license(new License().name("MIT")
                        .url("https://maku.net")));

        openapi.addSecurityItem(new SecurityRequirement().addList("api_key"))
                .components(new Components().addSecuritySchemes("api_key",
                        new SecurityScheme()
                                .name("Authorization")
                                .type(SecurityScheme.Type.APIKEY)
                                .in(SecurityScheme.In.HEADER)));

        return openapi;
    }

}
