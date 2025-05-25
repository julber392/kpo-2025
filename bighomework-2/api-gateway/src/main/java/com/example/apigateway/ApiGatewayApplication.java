package com.example.apigateway;

import com.example.apigateway.config.WebClientConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;
/**
 * Главный класс приложения API Gateway.
 * Запускает Spring Boot приложение и подключает конфигурацию WebClient.
 */
@SpringBootApplication
@Import(WebClientConfig.class)
public class ApiGatewayApplication {
    public static void main(String[] args) {
        SpringApplication.run(ApiGatewayApplication.class, args);
    }
}
