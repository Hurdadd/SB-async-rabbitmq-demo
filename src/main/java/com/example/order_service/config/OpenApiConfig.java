package com.example.order_service.config;


import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("BNPL Order Service API")
                        .version("v1")
                        .description("API documentation for BNPL Order Management")
                        .contact(new Contact()
                                .name("Tech Team")
                                .email("support@bnpl.com")
                                .url("https://bnpl.com"))
                );
    }
}
