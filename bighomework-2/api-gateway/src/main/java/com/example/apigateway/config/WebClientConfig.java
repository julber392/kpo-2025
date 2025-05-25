package com.example.apigateway.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;
/**
 * Конфигурация WebClient для взаимодействия с другими микросервисами.
 */
@Configuration
public class WebClientConfig {

    @Value("${file.storage.service.url}")
    private String fileStorageServiceUrl;

    @Value("${file.analysis.service.url}")
    private String fileAnalysisServiceUrl;
    /**
     * Создает WebClient для File Storage Service.
     */
    @Bean
    public WebClient fileStorageWebClient() {
        return WebClient.builder()
                .baseUrl(fileStorageServiceUrl)
                .build();
    }
    /**
     * Создает WebClient для File Analysis Service.
     */
    @Bean
    public WebClient fileAnalysisWebClient() {
        return WebClient.builder()
                .baseUrl(fileAnalysisServiceUrl)
                .build();
    }
}
