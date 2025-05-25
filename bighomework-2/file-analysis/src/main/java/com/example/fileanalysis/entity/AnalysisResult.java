package com.example.fileanalysis.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "analysis_results")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AnalysisResult {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "file_id", nullable = false, unique = true)
    private Long fileId;

    @Column(name = "paragraph_count", nullable = false)
    private int paragraphCount;

    @Column(name = "word_count", nullable = false)
    private int wordCount;

    @Column(name = "character_count", nullable = false)
    private int characterCount;

    public AnalysisResult(Long fileId, int paragraphCount, int wordCount, int characterCount) {
        this.fileId = fileId;
        this.paragraphCount = paragraphCount;
        this.wordCount = wordCount;
        this.characterCount = characterCount;
    }
}
