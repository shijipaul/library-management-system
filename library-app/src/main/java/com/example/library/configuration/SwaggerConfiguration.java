package com.example.library.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;

@Configuration
public class SwaggerConfiguration {
    @Bean
    OpenAPI libraryManagementOpenAPI() {
		return new OpenAPI().info(new Info()
					.description("API Documentation for Library Managemnt System")
					.title("Library Managemnt System")
					.version("1.0")
					.license(new License().name("Apache 2.0").url("https://springdoc.org"))
				);
	}

}
