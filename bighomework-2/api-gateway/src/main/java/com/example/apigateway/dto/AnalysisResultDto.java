package com.example.apigateway.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AnalysisResultDto {
    private Long fileId;
    private int paragraphCount;
    private int wordCount;
    private int characterCount;
}
