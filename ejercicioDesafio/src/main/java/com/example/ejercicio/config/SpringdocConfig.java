package com.example.ejercicio.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;

@Configuration
public class SpringdocConfig {

	  @Value("${info.build.version}")
	  private String version;
	  @Value("${info.build.title}")
	  private String title;

	  @Value("${info.build.description}")
	  private String description;
	
    @Bean
    OpenAPI openAPI() {
        return new OpenAPI().info(new Info().title(title)
                .version(version).description(description)
                .contact(new Contact().name("Carlos Rodriguez")
                )
                .license(new License().name("Apache License Version 2.0")));
    }


}
