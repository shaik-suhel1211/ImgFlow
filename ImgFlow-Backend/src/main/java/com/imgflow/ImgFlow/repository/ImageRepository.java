package com.imgflow.ImgFlow.repository;


import com.imgflow.ImgFlow.entity.Image;
import com.imgflow.ImgFlow.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ImageRepository extends JpaRepository<Image,Long> {
      List<Image> findByUser(User user);
      Page<Image> findByTitleContainingIgnoreCase(String title, Pageable pageable);
}
