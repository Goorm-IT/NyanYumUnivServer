package com.nyanyumserver.nyuimg.Service.impl;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.*;
import com.nyanyumserver.nyuimg.Service.ImageService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import lombok.RequiredArgsConstructor;


import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@RequiredArgsConstructor
@Service("ImageService")
public class ImageServiceImpl implements ImageService {
    private final AmazonS3 amazonS3;
    private final String UID_PREFIX = "/";
    private final String FILE_EXTENSION_SEPARATOR = ".";

    @Value("${cloud.aws.s3.bucket}")
    private String bucket;

    private void validateFileExists(MultipartFile multipartFile) {
        if (multipartFile.isEmpty()) {
            throw new IllegalArgumentException("The upload file is empty"); // Image Not Exist
        }
    }

    private String buildFileName(String uid, String originalFileName) {
        int fileExtensionIndex = originalFileName.lastIndexOf(FILE_EXTENSION_SEPARATOR);
        String fileExtension = originalFileName.substring(fileExtensionIndex);
        String fileName = originalFileName.substring(0, fileExtensionIndex);

        return uid + UID_PREFIX + fileName + fileExtension;
    }

    private String buildObjectKey(String uid) {
        System.out.format("Objects in S3 bucket %s:\n", bucket);
        ListObjectsV2Result result = amazonS3.listObjectsV2(bucket);
        List<S3ObjectSummary> objects = result.getObjectSummaries(); // Objects List
        for (S3ObjectSummary os : objects) {
            String object = os.getKey();
            int uidPrefixIndex = object.indexOf(UID_PREFIX);
            String uidCon = object.substring(0, uidPrefixIndex); // Extract uid part
            if (uidCon.equals(uid)) {
                return object;
            }
        }
        return null;
        /**
         * Load S3 default Image
         */
//        URI redirectUri = new URI("");
//        final StatusResponse response = new StatusResponse(StatusCode.SEE_OTHER, redirectUri);
//        return new ResponseEntity<>(response, HttpStatus.SEE_OTHER);
//        throw new IllegalArgumentException("Image Not Exist"); // Image Not Exist -> default Image

    }

    public String uploadImage(String uid, MultipartFile multipartFile) {
        validateFileExists(multipartFile);

        String fileName = buildFileName(uid, multipartFile.getOriginalFilename());

        if (buildObjectKey(uid) != null){ // 파일이 이미 있을 경우 삭제 먼저 진행
            deleteImage(uid);
        }

        ObjectMetadata objectMetadata = new ObjectMetadata();
        objectMetadata.setContentType(multipartFile.getContentType());

        try (InputStream inputStream = multipartFile.getInputStream()) {
            amazonS3.putObject(new PutObjectRequest(bucket, fileName, inputStream, objectMetadata)
                    .withCannedAcl(CannedAccessControlList.PublicRead));
            return downloadImage(uid);

        } catch (IOException e) { // 파일 변환 에러
            e.printStackTrace();
            throw new IllegalArgumentException(String.format("Converting File Error (uid : %s)", multipartFile.getOriginalFilename()));
        }
    }


    public String downloadImage(String uid) {
            String resourcePath = amazonS3.getUrl(bucket, buildObjectKey(uid)).toString();
            if (buildObjectKey(uid) == null) {
                throw new NullPointerException(String.format("Not Found Image (uid : %s)", uid));
            }
            System.out.println("imageUrl : " + resourcePath);
            return resourcePath;
    }

    public void deleteImage(String uid) {
        String deleteObjectKey = buildObjectKey(uid);
        if (deleteObjectKey == null) {
            throw new NullPointerException(String.format("Delete Request Rejected For Not Found Image(uid : %s)", uid));
        }
        amazonS3.deleteObject(bucket, deleteObjectKey);
    }
}
