package com.imgflow.ImgFlow.service;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;

@Service
public class CloudinaryService {

    private final Cloudinary cloudinary;

    public CloudinaryService(Cloudinary cloudinary) {
        this.cloudinary = cloudinary;
    }

    public String uploadFile(MultipartFile file) throws IOException {
        Map uploadResult = cloudinary.uploader().upload(file.getBytes(), ObjectUtils.emptyMap());
        return uploadResult.get("secure_url").toString(); // public URL
    }

    public void deleteFile(String fileUrl) throws IOException {
        String publicId = fileUrl.substring(fileUrl.lastIndexOf("/") + 1, fileUrl.lastIndexOf(".")); // extracting the public id from the url which is then used to delete the image from cloud
        cloudinary.uploader().destroy(publicId, ObjectUtils.emptyMap());
    }

}

