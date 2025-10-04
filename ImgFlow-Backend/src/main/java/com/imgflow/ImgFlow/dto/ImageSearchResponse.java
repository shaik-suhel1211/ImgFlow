package com.imgflow.ImgFlow.dto;


import com.imgflow.ImgFlow.entity.Image;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class ImageSearchResponse {
    private List<Image> images;
    private int page;
    private int totalPages;
}
