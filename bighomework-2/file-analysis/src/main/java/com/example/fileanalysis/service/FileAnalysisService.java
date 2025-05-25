package com.example.fileanalysis.service;

import com.example.fileanalysis.entity.AnalysisResult;

public interface FileAnalysisService {
    AnalysisResult analyzeFile(Long fileId);
    AnalysisResult getAnalysisResult(Long fileId);
}
