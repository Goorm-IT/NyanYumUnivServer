package com.nyanyumserver.nyanyumserver.Service;

import org.springframework.web.multipart.MultipartFile;

public interface ImageService {
    String updateImage(MultipartFile file, String id, String option);
    String loadImage(String id, String option);
    void deleteImage(String id, String option);
}
