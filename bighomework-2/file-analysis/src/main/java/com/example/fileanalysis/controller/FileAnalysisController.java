package com.example.fileanalysis.controller;
import com.example.fileanalysis.dto.AnalysisResultDto;
import com.example.fileanalysis.entity.AnalysisResult;
import com.example.fileanalysis.service.FileAnalysisService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
/**
 * Контроллер для обработки запросов анализа файлов.
 * Предоставляет API для запуска анализа и получения результатов.
 */
@RestController
@RequestMapping("/analysis")
public class FileAnalysisController {

    private final FileAnalysisService fileAnalysisService;

    public FileAnalysisController(FileAnalysisService fileAnalysisService) {
        this.fileAnalysisService = fileAnalysisService;
    }
    /**
     * Запускает анализ файла по его ID.
     * @param fileId ID файла для анализа
     * @return Результаты анализа (количество абзацев, слов, символов)
     */
    @PostMapping("/{fileId}")
    public ResponseEntity<AnalysisResultDto> analyzeFile(@PathVariable Long fileId) {
        try {
            AnalysisResult result = fileAnalysisService.analyzeFile(fileId);
            return ResponseEntity.ok(mapToDto(result));
        } catch (Exception e) {
            return ResponseEntity.internalServerError()
                    .body(new AnalysisResultDto(fileId, 0, 0, 0));
        }
    }
    /**
     * Получает результаты анализа по ID файла.
     * @param fileId ID файла
     * @return Сохраненные результаты анализа
     */
    @GetMapping("/{fileId}")
    public ResponseEntity<AnalysisResultDto> getAnalysisResult(@PathVariable Long fileId) {
        try {
            AnalysisResult result = fileAnalysisService.getAnalysisResult(fileId);
            return ResponseEntity.ok(mapToDto(result));
        } catch (Exception e) {
            return ResponseEntity.internalServerError()
                    .body(new AnalysisResultDto(fileId, 0, 0, 0));
        }
    }
    /**
     * Преобразует сущность AnalysisResult в DTO.
     * @param result Результат анализа
     * @return DTO с результатами анализа
     */
    private AnalysisResultDto mapToDto(AnalysisResult result) {
        return new AnalysisResultDto(
                result.getFileId(),
                result.getParagraphCount(),
                result.getWordCount(),
                result.getCharacterCount());
    }
}
