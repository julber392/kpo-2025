package com.example.filestorage.service;

import com.example.filestorage.entity.StoredFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface FileStorageService {
    StoredFile storeFile(MultipartFile file) throws IOException;
    StoredFile getFile(Long fileId);
    String getFileContent(Long fileId);
}