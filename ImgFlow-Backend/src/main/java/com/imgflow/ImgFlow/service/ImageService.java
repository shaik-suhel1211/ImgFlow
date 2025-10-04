package com.imgflow.ImgFlow.service;


import com.imgflow.ImgFlow.dto.ImageUploadRequest;
import com.imgflow.ImgFlow.entity.Image;
import com.imgflow.ImgFlow.entity.User;
import com.imgflow.ImgFlow.repository.ImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
public class ImageService {

    @Autowired
    private ImageRepository imageRepository;

    @Autowired
    private CloudinaryService cloudinaryService;

    public Image uploadImage(User user, ImageUploadRequest request) throws Exception {
        Image image = new Image();
        image.setUser(user);
        image.setTitle(request.getTitle());

        if (request.getFile() != null && !request.getFile().isEmpty()) {
            MultipartFile file = request.getFile();      //the file from request is of multipartFile type
             String fileUrl=cloudinaryService.uploadFile(file);
            image.setUrl(fileUrl);   //url is stored in db

        } else if (request.getUrl() != null) {
            image.setUrl(request.getUrl());
        } else {
            throw new Exception("No file or URL provided");
        }

        return imageRepository.save(image);
    }


    public List<Image> getAllUserImages(User user){
        List<Image> images=imageRepository.findByUser(user);
      return images;
    }

    public void deleteImage(Long id, User user) throws Exception {
        Image image = imageRepository.findById(id)
                .orElseThrow(() -> new Exception("Image not found"));

        // Optional: ensure user owns the image
        if (!image.getUser().getUserid().equals(user.getUserid())) {
            throw new Exception("You are not allowed to delete this image");
        }
        //delete from cloud
        if (image.getUrl() != null) {
            cloudinaryService.deleteFile(image.getUrl());
        }

        // Delete from DB
        imageRepository.delete(image);         //image deleted from DB
    }

}

