package com.example.filestorage.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FileContentResponse {
    private Long fileId;
    private String fileName;
    private String content;
}
