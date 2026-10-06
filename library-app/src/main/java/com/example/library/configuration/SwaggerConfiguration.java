package com.example.library.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;

@Configuration
public class SwaggerConfiguration {
    @Bean
    OpenAPI libraryManagementOpenAPI() {
    	final String securitySchemeName = "bearerAuth";
        return new OpenAPI()
                .info(new Info()
                        .description("API Documentation for Library Management System")
                        .title("Library Management System")
                        .version("1.0")
                        .license(new License().name("Apache 2.0").url("https://springdoc.org"))
                )
                // 1. Define the security scheme type as HTTP Bearer / JWT
                .components(new Components()
                        .addSecuritySchemes(securitySchemeName, new SecurityScheme()
                                .name(securitySchemeName)
                                .type(SecurityScheme.Type.HTTP)
                                .scheme("bearer")
                                .bearerFormat("JWT")
                        )
                )
                // 2. Apply the security requirement globally to all endpoints (like your addBook API)
                .addSecurityItem(new SecurityRequirement().addList(securitySchemeName));
    
	}

}
