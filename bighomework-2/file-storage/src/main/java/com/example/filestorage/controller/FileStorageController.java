package com.example.filestorage.controller;

import com.example.filestorage.dto.FileContentResponse;
import com.example.filestorage.dto.FileUploadResponse;
import com.example.filestorage.entity.StoredFile;
import com.example.filestorage.service.FileStorageService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
/**
 * Контроллер для обработки запросов, связанных с хранением файлов.
 * Предоставляет API для загрузки и получения файлов.
 */
@RestController
@RequestMapping("/files")
public class FileStorageController {

    private final FileStorageService fileStorageService;

    public FileStorageController(FileStorageService fileStorageService) {
        this.fileStorageService = fileStorageService;
    }
    /**
     * Загружает файл в хранилище.
     * @param file Файл для загрузки
     * @return Результат загрузки с ID и именем файла
     */
    @PostMapping("/upload")
    public ResponseEntity<FileUploadResponse> uploadFile(@RequestParam("file") MultipartFile file) {
        try {
            StoredFile storedFile = fileStorageService.storeFile(file);
            return ResponseEntity.ok(new FileUploadResponse(
                    storedFile.getId(),
                    storedFile.getFileName(),
                    "File uploaded successfully"));
        } catch (IOException e) {
            return ResponseEntity.internalServerError()
                    .body(new FileUploadResponse(null, null, "Failed to upload file: " + e.getMessage()));
        }
    }
    /**
     * Получает содержимое файла по ID.
     * @param fileId ID файла
     * @return Содержимое файла с метаданными
     */
    @GetMapping("/{fileId}/content")
    public ResponseEntity<FileContentResponse> getFileContent(@PathVariable Long fileId) {
        try {
            String content = fileStorageService.getFileContent(fileId);
            StoredFile file = fileStorageService.getFile(fileId);
            return ResponseEntity.ok(new FileContentResponse(file.getId(), file.getFileName(), content));
        } catch (Exception e) {
            return ResponseEntity.internalServerError()
                    .body(new FileContentResponse(fileId, null, "Failed to get file content: " + e.getMessage()));
        }
    }
}