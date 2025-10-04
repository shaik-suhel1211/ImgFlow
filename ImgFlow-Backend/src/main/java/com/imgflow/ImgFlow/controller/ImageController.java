package com.imgflow.ImgFlow.controller;

import com.imgflow.ImgFlow.dto.ImageUploadRequest;
import com.imgflow.ImgFlow.entity.Image;
import com.imgflow.ImgFlow.entity.User;
import com.imgflow.ImgFlow.repository.UserRepository;
import com.imgflow.ImgFlow.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/images")
public class ImageController {

    @Autowired
    private ImageService imageService;

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/upload")
    public Image uploadImage(Authentication auth, @ModelAttribute ImageUploadRequest request) throws Exception {
        User user = (User) auth.getPrincipal();   //spring injects auth obj(means the user is in SCH) this way we can access a logged in user (also directly through SCH)
        return imageService.uploadImage(user, request);
    }

    @GetMapping("/myImages")
    public List<Image> allUserImages(Authentication auth){
        User user=(User)auth.getPrincipal();
          return imageService.getAllUserImages(user);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteImage(@PathVariable Long id, Authentication auth) {
        User user = (User) auth.getPrincipal();
        try {
            imageService.deleteImage(id, user);
            return ResponseEntity.ok().body("Image deleted successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
}

