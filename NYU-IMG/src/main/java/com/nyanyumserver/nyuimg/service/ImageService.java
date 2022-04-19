package com.nyanyumserver.nyuimg.service;

import org.springframework.web.multipart.MultipartFile;

public interface ImageService {
    String uploadImage(String id, String option, MultipartFile multipartFile);
    String downloadImage(String id, String option);
    void deleteImage(String id, String option);
}
