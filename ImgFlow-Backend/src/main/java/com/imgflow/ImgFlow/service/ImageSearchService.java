package com.imgflow.ImgFlow.service;

import com.imgflow.ImgFlow.dto.ImageSearchResponse;
import com.imgflow.ImgFlow.entity.Image;
import com.imgflow.ImgFlow.entity.User;
import com.imgflow.ImgFlow.repository.ImageRepository;
import com.imgflow.ImgFlow.repository.UserRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
public class ImageSearchService {

    private final ImageRepository imageRepository;
    private final UserRepository userRepository;

    public ImageSearchService(ImageRepository imageRepository, UserRepository userRepository) {
        this.imageRepository = imageRepository;
        this.userRepository = userRepository;
    }

    public ImageSearchResponse searchImages(String accessToken, String query, int page, int size) throws Exception {
        // 1. Validate access token
        User user = userRepository.findByAccessToken(accessToken)
                .orElseThrow(() -> new Exception("Invalid access token"));

        // 2. Query images with pagination
        Page<Image> imagePage = imageRepository.findByTitleContainingIgnoreCase(query, PageRequest.of(page-1, size)); //since page is zero based so page=1 is page =0;

        // 3. Return paginated result
        return new ImageSearchResponse(imagePage.getContent(), page, imagePage.getTotalPages());
    }
}

