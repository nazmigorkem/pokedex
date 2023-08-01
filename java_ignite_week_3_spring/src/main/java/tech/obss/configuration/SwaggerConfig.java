package tech.obss.configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Value("${application.version}")
    private String applicationVersion;

    @Bean
    OpenAPI openAPIConfiguration() {
        return new OpenAPI().info(
                new Info()
                        .title("Java Ignite Week 3 Spring Boot Application")
                        .version(applicationVersion)
        );
    }
}
