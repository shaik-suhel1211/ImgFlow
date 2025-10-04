package com.imgflow.ImgFlow.dto;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class ImageUploadRequest {
    private String title;
    private MultipartFile file; // For device uploads
    private String url;         // For URL uploads
}
