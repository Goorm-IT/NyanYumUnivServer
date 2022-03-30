package com.nyanyumserver.nyuimg.Controller;

import com.nyanyumserver.nyuimg.Global.status.StatusCode;
import com.nyanyumserver.nyuimg.Global.status.StatusResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import com.nyanyumserver.nyuimg.Service.ImageService;

@RequiredArgsConstructor
@RestController
public class ImageController {
    private final ImageService imageService;

    /**
     * 이미지 변경하기
     *
     * body {
     *     "uid" : "uid",
     *     "file" : "file"
     * }
     * @param uid
     * @param multipartFile
     */
    @PostMapping("/auth/updateProfileImage")
    public ResponseEntity<StatusResponse> uploadImage(
            @RequestParam("uid") String uid,
            @RequestPart(value = "file") MultipartFile multipartFile) {
            String resourcePath = imageService.uploadImage(uid, multipartFile);
            final StatusResponse response = new StatusResponse(StatusCode.OK, resourcePath);
            return new ResponseEntity<>(response, HttpStatus.OK);
    }

    /**
     * 이미지 가져오기
     *
     * @param uid
     * @return ImageURI
     */
    @GetMapping("/auth/downloadProfileImage")
    public ResponseEntity<StatusResponse> downloadImage(
            @RequestParam("uid") String uid) {
            String resourcePath = imageService.downloadImage(uid);
            final StatusResponse response = new StatusResponse(StatusCode.OK, resourcePath);
            return new ResponseEntity<>(response, HttpStatus.OK);
    }

    /**
     * 이미지 삭제하기
     *
     * @param uid
     */
    @DeleteMapping("/auth/deleteProfileImage")
    public ResponseEntity<StatusResponse> deleteImage(
            @RequestParam("uid") String uid) {
            imageService.deleteImage(uid);
            final StatusResponse response = new StatusResponse(StatusCode.OK, uid);
            return new ResponseEntity<>(response, HttpStatus.OK);
    }

}