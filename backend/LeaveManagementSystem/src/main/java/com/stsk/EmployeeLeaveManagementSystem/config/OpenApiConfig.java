package com.stsk.EmployeeLeaveManagementSystem.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {
    /*creating a bean of OpenAPI for FE*/
    @Bean
    public OpenAPI elmsOpenApi() {
        return new OpenAPI()
                .info(new Info()
                        .title("Employee Leave Management System OpenAPI specification ")
                        .description("API contract for employee, Admin and Manager Operations")
                        .version("1.0.0"));
    }
}
