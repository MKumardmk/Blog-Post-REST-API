package com.mkumar.blogpost.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI springShopOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("E-Commerce Application")
                        .description("Backend APIs for BlogPost app")
                        .version("v1.0.0")
                        .contact(new Contact()
                                .name("Mohan Kumar")
                                .url("https://MKumardmk.github.io")
                                .email("mohandmk.mk@gmail.com"))
                        .license(new License()
                                .name("License")
                                .url("http://example.com/license")))
                .externalDocs(new ExternalDocumentation()
                        .description("Blog Post App Documentation")
                        .url("http://localhost:8080/swagger-ui/index.html"));
    }
}