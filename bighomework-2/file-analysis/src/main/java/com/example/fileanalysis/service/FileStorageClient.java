package com.example.fileanalysis.service;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class FileStorageClient {

    private final RestTemplate restTemplate;
    private final String fileStorageServiceUrl;

    public FileStorageClient(RestTemplate restTemplate, @Value("${file.storage.service.url}") String fileStorageServiceUrl) {
        this.restTemplate = restTemplate;
        this.fileStorageServiceUrl = fileStorageServiceUrl;
    }

    public String getFileContent(Long fileId) {
        String url = fileStorageServiceUrl + "/files/" + fileId + "/content";
        return restTemplate.getForObject(url, String.class);
    }
}
