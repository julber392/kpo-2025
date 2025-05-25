package com.example.filestorage;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
/**
 * Главный класс приложения File Storage Service.
 * Запускает Spring Boot приложение с поддержкой JPA репозиториев.
 */
@SpringBootApplication
@EnableJpaRepositories
public class FileStorageApplication {
    public static void main(String[] args) {
        SpringApplication.run(FileStorageApplication.class, args);
    }
}
