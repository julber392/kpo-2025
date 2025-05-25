package com.example.fileanalysis;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.client.RestTemplate;
/**
 * Главный класс приложения File Analysis Service.
 * Запускает Spring Boot приложение с поддержкой JPA и настраивает RestTemplate.
 */
@SpringBootApplication
@EnableJpaRepositories
public class FileAnalysisApplication {
	public static void main(String[] args) {
		SpringApplication.run(FileAnalysisApplication.class, args);
	}

	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}
}
