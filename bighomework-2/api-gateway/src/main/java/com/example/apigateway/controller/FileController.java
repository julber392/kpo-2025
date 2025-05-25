package com.example.apigateway.controller;

import com.example.apigateway.dto.AnalysisResultDto;
import com.example.apigateway.dto.FileContentDto;
import com.example.apigateway.dto.FileUploadResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

/**
 * Контроллер для обработки запросов, связанных с файлами.
 * Обеспечивает API для загрузки, получения и анализа файлов.
 */
@RestController
@RequestMapping("/api/files")
public class FileController {

    private final WebClient fileStorageWebClient;
    private final WebClient fileAnalysisWebClient;

    public FileController(WebClient fileStorageWebClient, WebClient fileAnalysisWebClient) {
        this.fileStorageWebClient = fileStorageWebClient;
        this.fileAnalysisWebClient = fileAnalysisWebClient;
    }

    /**
     * Проверка работоспособности сервиса.
     * @return Приветственное сообщение
     */
    @GetMapping
    public Mono<String> welcome() {
        return Mono.just("Text Scanner API is running. Use /upload, /{fileId}/content, /{fileId}/analyze endpoints");
    }

    /**
     * Загружает файл в систему.
     * @param file Файл для загрузки
     * @return Результат загрузки файла
     */
    @PostMapping("/upload")
    public Mono<ResponseEntity<FileUploadResponse>> uploadFile(@RequestParam("file") MultipartFile file) {
        return fileStorageWebClient.post()
                .uri("/files/upload")
                .bodyValue(file)
                .retrieve()
                .onStatus(HttpStatusCode::isError, response ->
                        Mono.error(new RuntimeException("File storage service error: " + response.statusCode())))
                .bodyToMono(FileUploadResponse.class)
                .map(ResponseEntity::ok)
                .onErrorResume(e -> Mono.just(
                        ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                                .body(new FileUploadResponse(null, null, "Error: " + e.getMessage()))));
    }

    /**
     * Получает содержимое файла.
     * @param fileId ID файла
     * @return Содержимое файла
     */
    @GetMapping("/{fileId}/content")
    public Mono<ResponseEntity<FileContentDto>> getFileContent(@PathVariable Long fileId) {
        return fileStorageWebClient.get()
                .uri("/files/{fileId}/content", fileId)
                .retrieve()
                .onStatus(HttpStatusCode::isError, response ->
                        Mono.error(new RuntimeException("File storage service error: " + response.statusCode())))
                .bodyToMono(FileContentDto.class)
                .map(ResponseEntity::ok)
                .onErrorResume(e -> Mono.just(ResponseEntity.internalServerError().build()));
    }

    /**
     * Запускает анализ файла.
     * @param fileId ID файла для анализа
     * @return Результаты анализа
     */
    @PostMapping("/{fileId}/analyze")
    public Mono<ResponseEntity<AnalysisResultDto>> analyzeFile(@PathVariable Long fileId) {
        return fileAnalysisWebClient.post()
                .uri("/analysis/{fileId}", fileId)
                .retrieve()
                .onStatus(HttpStatusCode::isError, response ->
                        Mono.error(new RuntimeException("File analysis service error: " + response.statusCode())))
                .bodyToMono(AnalysisResultDto.class)
                .map(ResponseEntity::ok)
                .onErrorResume(e -> Mono.just(ResponseEntity.internalServerError().build()));
    }

    /**
     * Получает результаты анализа файла.
     * @param fileId ID файла
     * @return Результаты анализа
     */
    @GetMapping("/{fileId}/analysis")
    public Mono<ResponseEntity<AnalysisResultDto>> getAnalysisResult(@PathVariable Long fileId) {
        return fileAnalysisWebClient.get()
                .uri("/analysis/{fileId}", fileId)
                .retrieve()
                .onStatus(HttpStatusCode::isError, response ->
                        Mono.error(new RuntimeException("File analysis service error: " + response.statusCode())))
                .bodyToMono(AnalysisResultDto.class)
                .map(ResponseEntity::ok)
                .onErrorResume(e -> Mono.just(ResponseEntity.internalServerError().build()));
    }
}