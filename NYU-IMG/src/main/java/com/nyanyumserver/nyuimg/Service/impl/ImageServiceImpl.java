package com.nyanyumserver.nyuimg.Service.impl;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.*;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.util.IOUtils;
import com.nyanyumserver.nyuimg.Service.ImageService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import lombok.RequiredArgsConstructor;


import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service("ImageService")
public class ImageServiceImpl implements ImageService {
    private final AmazonS3 amazonS3;
    private final String UID_PREFIX = "/";
    private final String FILE_EXTENSION_SEPARATOR = ".";

    @Value("${cloud.aws.s3.bucket}")
    private String bucket;

    private String buildFileName(String uid, String originalFileName) {
        int fileExtensionIndex = originalFileName.lastIndexOf(FILE_EXTENSION_SEPARATOR);
        String fileExtension = originalFileName.substring(fileExtensionIndex);
        String fileName = originalFileName.substring(0, fileExtensionIndex);

        return uid + UID_PREFIX + fileName + fileExtension;
    }

    public String uploadImage(String uid, MultipartFile multipartFile) {
        validateFileExists(multipartFile);

        String fileName = buildFileName(uid, multipartFile.getOriginalFilename());

        ObjectMetadata objectMetadata = new ObjectMetadata();
        objectMetadata.setContentType(multipartFile.getContentType());

        try (InputStream inputStream = multipartFile.getInputStream()) {
            amazonS3.putObject(new PutObjectRequest(bucket, fileName, inputStream, objectMetadata)
                    .withCannedAcl(CannedAccessControlList.PublicRead));
        } catch (IOException e) {
            throw new IllegalArgumentException(String.format("Converting File Error (%s)", multipartFile.getOriginalFilename()));
        }

        return amazonS3.getUrl(bucket, fileName).toString();
    }

    private void validateFileExists(MultipartFile multipartFile) {
        if (multipartFile.isEmpty()) {
            throw new IllegalArgumentException("Nothing File");
        }
    }

    private S3Object buildObjectKey(String uid) {
        System.out.format("Objects in S3 bucket %s:\n", bucket);
        ListObjectsV2Result result = amazonS3.listObjectsV2(bucket);
        List<S3ObjectSummary> objects = result.getObjectSummaries(); // Objects List
        for (S3ObjectSummary os : objects) {
            String object = os.getKey();
            int uidPrefixIndex = object.indexOf(UID_PREFIX);
            String fileName = object.substring(0, uidPrefixIndex);
            if (fileName.equals(uid)) {
                return amazonS3.getObject(bucket, object);
            }
        }
        return null;
    }

    public byte[] downloadImage(String uid) {
        S3ObjectInputStream inputStream = buildObjectKey(uid).getObjectContent();
        try {
            return IOUtils.toByteArray(inputStream);
        } catch (IOException e) {
            throw new IllegalArgumentException("Nothing File");
        }
    }

    public void deleteImage(String uid) {
        try {
            amazonS3.deleteObject(bucket, buildObjectKey(uid).getKey());
        } catch (AmazonServiceException e) {
            throw new IllegalArgumentException("Nothing File");
        }
    }
}
