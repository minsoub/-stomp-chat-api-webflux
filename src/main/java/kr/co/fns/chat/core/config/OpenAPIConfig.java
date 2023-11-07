package kr.co.fns.chat.core.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;
import java.util.Map;

@Configuration
public class OpenAPIConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        var authHeader = Map.of(
                "bearAuth",
                new SecurityScheme()
                        .type(SecurityScheme.Type.APIKEY)
                        .scheme("access_token")
                        .bearerFormat("JWT")
                        .in(SecurityScheme.In.HEADER).name("access_token")
        );
        var schemaRequirement = new SecurityRequirement().addList("bearAuth");

        return new OpenAPI()
                .components(new Components().securitySchemes(authHeader))
                .security(List.of(schemaRequirement));

    }
}
