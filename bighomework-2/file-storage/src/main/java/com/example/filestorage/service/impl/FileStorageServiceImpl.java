package com.example.filestorage.service.impl;

import com.example.filestorage.entity.StoredFile;
import com.example.filestorage.exception.FileNotFoundException;
import com.example.filestorage.repository.FileStorageRepository;
import com.example.filestorage.service.FileStorageService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

@Service
public class FileStorageServiceImpl implements FileStorageService {

    private final FileStorageRepository fileStorageRepository;

    public FileStorageServiceImpl(FileStorageRepository fileStorageRepository) {
        this.fileStorageRepository = fileStorageRepository;
    }

    @Override
    public StoredFile storeFile(MultipartFile file) throws IOException {
        String content = new String(file.getBytes(), StandardCharsets.UTF_8);
        StoredFile storedFile = new StoredFile(file.getOriginalFilename(), content);
        return fileStorageRepository.save(storedFile);
    }

    @Override
    public StoredFile getFile(Long fileId) {
        return fileStorageRepository.findById(fileId)
                .orElseThrow(() -> new FileNotFoundException("File not found with id: " + fileId));
    }

    @Override
    public String getFileContent(Long fileId) {
        return getFile(fileId).getContent();
    }
}
