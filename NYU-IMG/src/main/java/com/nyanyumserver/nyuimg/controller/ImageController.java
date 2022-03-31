package com.nyanyumserver.nyuimg.controller;

import com.nyanyumserver.nyuimg.global.status.StatusCode;
import com.nyanyumserver.nyuimg.global.status.StatusResponse;
import io.swagger.annotations.*;
import lombok.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import com.nyanyumserver.nyuimg.service.ImageService;


@RequiredArgsConstructor
@RestController
@RequestMapping("/auth")
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
    @ApiResponses({
            @ApiResponse(code=200, message="이미지 업데이트 완료"),
            @ApiResponse(code=415, message="파일 형식 미지원 (지원가능 확장자  : '.bmp', '.jpeg', '.jpg', '.png', '.tif', '.tiff', '.svg')"),
            @ApiResponse(code=416, message="파일 용량 초과 (용량 제한 : 3MB)"),
            @ApiResponse(code=417, message="사용자 전송 파일 없음")
    })
    @ApiImplicitParam(name = "uid", value = "사용자 uid", required = true, dataType = "String")
    @ApiOperation(value = "이미지 변경하기")
    @PostMapping("/updateProfileImage")
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
    @ApiResponses({
            @ApiResponse(code=200, message="이미지 다운로드 완료"),
            @ApiResponse(code=204, message="S3 버킷내에 해당 uid 사용자 이미지 없음")
    })
    @ApiImplicitParam(name = "uid", value = "사용자 uid", required = true, dataType = "String")
    @ApiOperation(value = "이미지 가져오기")
    @GetMapping("/downloadProfileImage")
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
    @ApiResponses({
            @ApiResponse(code=200, message="이미지 삭제 완료"),
            @ApiResponse(code=204, message="S3 버킷내에 해당 uid 사용자 이미지 없음")
    })
    @ApiImplicitParam(name = "uid", value = "사용자 uid", required = true, dataType = "String")
    @ApiOperation(value = "이미지 삭제하기")
    @DeleteMapping("/deleteProfileImage")
    public ResponseEntity<StatusResponse> deleteImage(
            @RequestParam("uid") String uid) {
            imageService.deleteImage(uid);
            final StatusResponse response = new StatusResponse(StatusCode.OK, uid);
            return new ResponseEntity<>(response, HttpStatus.OK);
    }

}