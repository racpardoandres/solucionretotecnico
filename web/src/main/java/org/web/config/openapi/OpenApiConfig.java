package org.web.config.openapi;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public GroupedOpenApi apiGroup() {
        return GroupedOpenApi.builder()
                .group("v1")
                .packagesToScan("org.web.controller") // Escanear controladores
                .build();
    }

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("API de Autenticación")
                        .version("1.0")
                        .description("Documentación de la API de seguridad con Spring Boot y OpenAPI"))
                .addSecurityItem(new SecurityRequirement().addList("BearerAuth")) // 🔐 Requerir seguridad en las peticiones
                .components(new io.swagger.v3.oas.models.Components()
                        .addSecuritySchemes("BearerAuth", // 🔐 Esquema de seguridad JWT
                                new SecurityScheme()
                                        .name("BearerAuth")
                                        .type(SecurityScheme.Type.HTTP)
                                        .scheme("bearer")
                                        .bearerFormat("JWT")));
    }
}
