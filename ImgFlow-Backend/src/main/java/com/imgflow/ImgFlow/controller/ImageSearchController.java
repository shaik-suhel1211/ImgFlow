package com.imgflow.ImgFlow.controller;


import com.imgflow.ImgFlow.dto.ImageSearchResponse;
import com.imgflow.ImgFlow.service.ImageSearchService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/search")
public class ImageSearchController {

    private final ImageSearchService searchService;

    public ImageSearchController(ImageSearchService searchService) {
        this.searchService = searchService;
    }

    @GetMapping
    public ImageSearchResponse search(
            @RequestParam String accessToken,
            @RequestParam String query,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) throws Exception {
        return searchService.searchImages(accessToken, query, page, size);
    }
}

