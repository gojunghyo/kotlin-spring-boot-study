package com.example.demo.config

import io.swagger.v3.oas.annotations.OpenAPIDefinition
import io.swagger.v3.oas.models.OpenAPI
import io.swagger.v3.oas.models.info.Contact
import io.swagger.v3.oas.models.info.Info
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Profile

@Profile("!prod")
@Configuration
@OpenAPIDefinition
class `Spring-docConfig` {

    @Bean
    fun openAPI(): OpenAPI {
        val info = Info()
            .title("spring-boot-test-study API")
            .description("Spring boot test study Sample Application")
            .version("v0.0.1")
            .contact(Contact()
                .name("Junghyo Go")
                .email("gojgho@mz.co.kr")
                .url("https://github.com/marinbom2"))

        return OpenAPI().info(info)
    }
}
