package com.nyanyumserver.nyuimg.Controller;

import lombok.RequiredArgsConstructor;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import com.nyanyumserver.nyuimg.Service.ImageService;

@RequiredArgsConstructor
@RestController
public class ImageController {
    private final ImageService imageService;

    @GetMapping("/auth/downloadProfileImage")
    public ResponseEntity<ByteArrayResource> downloadImage(
            @RequestParam("uid") String uid) {
            byte[] data = imageService.downloadImage(uid);
        ByteArrayResource resource = new ByteArrayResource(data);
        HttpHeaders headers = buildHeaders(uid, data);

        return ResponseEntity
                .ok()
                .headers(headers)
                .body(resource);
    }

    private HttpHeaders buildHeaders(String resourcePath, byte[] data) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentLength(data.length);
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
//        headers.setContentDisposition(CommonUtils.createContentDisposition(uid));
        return headers;
    }

    @PostMapping("/auth/updateProfileImage")
    public String uploadImage(
            @RequestParam("uid") String uid,
            @RequestPart(value = "file") MultipartFile multipartFile) {
        return imageService.uploadImage(uid, multipartFile);
    }

    @DeleteMapping("/auth/deleteProfileImage")
    public void deleteImage(
            @RequestParam("uid") String uid) {
        imageService.deleteImage(uid);
    }
}