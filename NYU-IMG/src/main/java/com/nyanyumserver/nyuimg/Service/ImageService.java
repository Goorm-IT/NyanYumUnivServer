package com.nyanyumserver.nyuimg.Service;

import org.springframework.web.multipart.MultipartFile;

public interface ImageService {
    void uploadImage(String uid, MultipartFile multipartFile);
    String downloadImage(String uid);
    void deleteImage(String uid);

}
