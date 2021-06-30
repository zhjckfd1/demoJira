package com.example.demojira.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//localhost:8080/swagger-ui.html
@Configuration
public class SwaggerConfig {
    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(
                        new Info()
                                .title("demoJira")
                                .version("1.0.0")
                                .contact(
                                        new Contact()
                                                .email("jsonin@mmtr.ru")
                                                .name("Sonin")
                                )
                );

    }
}
