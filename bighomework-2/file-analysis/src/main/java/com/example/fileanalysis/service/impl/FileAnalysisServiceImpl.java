package com.example.fileanalysis.service.impl;

import com.example.fileanalysis.entity.AnalysisResult;
import com.example.fileanalysis.exception.AnalysisException;
import com.example.fileanalysis.repository.AnalysisResultRepository;
import com.example.fileanalysis.service.FileAnalysisService;
import com.example.fileanalysis.service.FileStorageClient;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class FileAnalysisServiceImpl implements FileAnalysisService {

    private final FileStorageClient fileStorageClient;
    private final AnalysisResultRepository analysisResultRepository;

    public FileAnalysisServiceImpl(FileStorageClient fileStorageClient,
                                   AnalysisResultRepository analysisResultRepository) {
        this.fileStorageClient = fileStorageClient;
        this.analysisResultRepository = analysisResultRepository;
    }

    @Override
    public AnalysisResult analyzeFile(Long fileId) {
        try {
            String content = fileStorageClient.getFileContent(fileId);

            int paragraphCount = content.split("\n\n").length;
            int wordCount = content.split("\\s+").length;
            int characterCount = content.length();

            AnalysisResult result = new AnalysisResult(
                    fileId,
                    paragraphCount,
                    wordCount,
                    characterCount);

            return analysisResultRepository.save(result);
        } catch (Exception e) {
            throw new AnalysisException("Failed to analyze file: " + e.getMessage(), e);
        }
    }

    @Override
    public AnalysisResult getAnalysisResult(Long fileId) {
        return analysisResultRepository.findByFileId(fileId)
                .orElseThrow(() -> new AnalysisException("Analysis result not found for file id: " + fileId));
    }
}
